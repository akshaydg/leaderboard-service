package com.intuit.craftproject.leaderboardservice.repository;

import com.intuit.craftproject.leaderboardservice.model.Leaderboard;
import com.intuit.craftproject.leaderboardservice.model.LeaderboardTypeEnum;

import java.util.Date;
import java.util.Optional;

public interface LeaderboardRepository {
    String addLeaderboard(String leaderboardName, Date createdAt, LeaderboardTypeEnum leaderboardTypeEnum);
    Optional<Leaderboard> getLeaderboard(String leaderboardId);
    Optional<Leaderboard> getLeaderboardByName(String leaderboardName);
}
