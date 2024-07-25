package com.intuit.craftproject.leaderboardservice.repository;

public interface UserDetailsCacheRepository {
    void save(String userId, String userName);
    String getUserName(String userId);
}
