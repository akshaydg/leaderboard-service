package com.intuit.craftproject.leaderboardservice.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserDetailsCacheRepository implements UserDetailsCacheRepository {

    private final ConcurrentHashMap<String, String> userDetailsMap;

    public InMemoryUserDetailsCacheRepository() {
        userDetailsMap = init();
    }

    private ConcurrentHashMap<String, String> init() {
        final ConcurrentHashMap<String, String> userDetailsMap;
        userDetailsMap = new ConcurrentHashMap<>();
        userDetailsMap.put("1", "John");
        userDetailsMap.put("2", "Doe");
        userDetailsMap.put("3", "Jane");
        userDetailsMap.put("4", "Smith");
        userDetailsMap.put("5", "Alice");
        userDetailsMap.put("6", "Bob");
        userDetailsMap.put("7", "Charlie");
        userDetailsMap.put("8", "David");
        userDetailsMap.put("9", "Eve");
        userDetailsMap.put("10", "Frank");
        return userDetailsMap;
    }

    @Override
    public void save(String userId, String userName) {
        userDetailsMap.put(userId, userName);
    }

    @Override
    public String getUserName(String userId) {
        return userDetailsMap.get(userId);
    }
}
