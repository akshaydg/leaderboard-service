package com.intuit.craftproject.leaderboardservice.service.leaderboardcalculationstrategy;

import com.intuit.craftproject.leaderboardservice.model.ScoreCard;
import com.intuit.craftproject.leaderboardservice.model.GameDetails;
import com.intuit.craftproject.leaderboardservice.service.rankingstrategy.RankingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class AllTimeLeaderboardCalculationService implements LeaderboardCalculationService {

    @Value("${leaderboard.max-size}")
    private final int maxSize;

    private final PriorityQueue<GameDetails> leaderboardPriorityQueue;

    private final RankingStrategy rankingStrategy;

    public AllTimeLeaderboardCalculationService(@Value("${leaderboard.max-size}") int maxSize, @Qualifier("rankingByUserIdStrategy") RankingStrategy rankingStrategy) {
        this.maxSize=maxSize;
        this.rankingStrategy = rankingStrategy;
        this.leaderboardPriorityQueue = new PriorityQueue<>(maxSize, (a, b) -> {
            if (a.score() == b.score()) {
                return rankingStrategy.compare(a, b);
            }
            return a.score() - b.score();
        });
    }

    @Override
    public void publishScoreToLeaderboard(GameDetails gameDetails) {
        if (leaderboardPriorityQueue.size() < maxSize) {
            leaderboardPriorityQueue.add(gameDetails);
        } else {
            GameDetails lowestScoreDetails = leaderboardPriorityQueue.peek();
            if (lowestScoreDetails!=null)
            {
                if(gameDetails.score() > lowestScoreDetails.score()) {
                    leaderboardPriorityQueue.poll();
                    leaderboardPriorityQueue.add(gameDetails);
                } else if(gameDetails.score() == lowestScoreDetails.score() && rankingStrategy.compare(gameDetails, lowestScoreDetails) < 0) {
                    leaderboardPriorityQueue.poll();
                    leaderboardPriorityQueue.add(gameDetails);
                }
            }
        }
    }

    public List<ScoreCard> getTopKLeaderboard(int size) {
        if(size > maxSize) {
            throw new IllegalArgumentException("Size cannot be greater than max size");
        }
        List<ScoreCard> sortedScores = new ArrayList<>(leaderboardPriorityQueue.stream().map(ScoreCard::fromGameDetails).toList());
        sortedScores.sort(Comparator.comparingInt(ScoreCard::score).reversed());
        return sortedScores.subList(0, Math.min(size, sortedScores.size()));
    }
}
