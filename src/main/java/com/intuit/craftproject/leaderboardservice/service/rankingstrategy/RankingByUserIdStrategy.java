package com.intuit.craftproject.leaderboardservice.service.rankingstrategy;

import com.intuit.craftproject.leaderboardservice.model.GameDetails;

public class RankingByUserIdStrategy implements RankingStrategy {
    @Override
    public int compare(GameDetails a, GameDetails b) {
        return a.userId().compareTo(b.userId());
    }
}