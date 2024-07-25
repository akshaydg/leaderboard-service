package com.intuit.craftproject.leaderboardservice.repository;

import com.intuit.craftproject.leaderboardservice.model.LeaderboardData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAllTimeLeaderboardDataCacheObserver implements LeaderboardDataCacheObserver {

    private final ConcurrentHashMap<String, LeaderboardData> leaderboards = new ConcurrentHashMap<>();

    @Override
    public void updateLeaderboardData(String leaderboardId, LeaderboardData leaderboard) {
        leaderboards.put(leaderboardId, leaderboard);
    }

    @Override
    public Optional<LeaderboardData> getTopKLeaderboardData(String leaderboardId) {
        return Optional.ofNullable(leaderboards.get(leaderboardId));
    }
}
