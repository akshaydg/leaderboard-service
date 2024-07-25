package com.intuit.craftproject.leaderboardservice.service.rankingstrategy;

import com.intuit.craftproject.leaderboardservice.model.GameDetails;

public interface RankingStrategy {
    int compare(GameDetails a, GameDetails b);
}