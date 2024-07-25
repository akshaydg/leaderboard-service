package com.intuit.craftproject.leaderboardservice.repository;

import com.intuit.craftproject.leaderboardservice.model.Leaderboard;
import com.intuit.craftproject.leaderboardservice.model.LeaderboardTypeEnum;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryLeaderboardRepository implements LeaderboardRepository {

    private final ConcurrentHashMap<String, Leaderboard> leaderboards = new ConcurrentHashMap<>();
    private final AtomicInteger lastLeaderboardId = new AtomicInteger(0);


    @Override
    public synchronized String addLeaderboard(String leaderboardName, Date createdAt, LeaderboardTypeEnum leaderboardTypeEnum) {
        int newLeaderboardId = lastLeaderboardId.incrementAndGet();
        Leaderboard leaderboard = new Leaderboard(String.valueOf(newLeaderboardId), leaderboardName, createdAt, leaderboardTypeEnum);
        leaderboards.put(String.valueOf(newLeaderboardId), leaderboard);
        return String.valueOf(newLeaderboardId);
    }

    public Optional<Leaderboard> getLeaderboard(String leaderboardId) {
        return Optional.ofNullable(leaderboards.get(leaderboardId));
    }

    @Override
    public Optional<Leaderboard> getLeaderboardByName(String leaderboardName) {
        return leaderboards.values().stream()
                .filter(leaderboard -> leaderboard.getLeaderboardName().equals(leaderboardName))
                .findFirst();
    }

}
