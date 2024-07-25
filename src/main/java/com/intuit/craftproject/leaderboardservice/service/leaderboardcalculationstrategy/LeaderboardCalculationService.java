package com.intuit.craftproject.leaderboardservice.service.leaderboardcalculationstrategy;


import com.intuit.craftproject.leaderboardservice.model.ScoreCard;
import com.intuit.craftproject.leaderboardservice.model.GameDetails;

import java.util.List;

public interface LeaderboardCalculationService {
    void publishScoreToLeaderboard(GameDetails gameDetails);
    List<ScoreCard> getTopKLeaderboard(int size);
}
