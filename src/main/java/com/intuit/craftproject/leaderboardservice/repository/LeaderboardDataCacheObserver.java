package com.intuit.craftproject.leaderboardservice.repository;

import com.intuit.craftproject.leaderboardservice.model.LeaderboardData;

import java.util.Optional;

// Observer Interface
public interface LeaderboardDataCacheObserver {
    Optional<LeaderboardData> getTopKLeaderboardData(String leaderboardId);
    void updateLeaderboardData(String leaderboardId, LeaderboardData leaderboardData);
}
