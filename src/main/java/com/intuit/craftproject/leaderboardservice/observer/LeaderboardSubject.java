package com.intuit.craftproject.leaderboardservice.observer;

import com.intuit.craftproject.leaderboardservice.model.LeaderboardData;
import com.intuit.craftproject.leaderboardservice.repository.LeaderboardDataCacheObserver;

public interface LeaderboardSubject {
    void attachObserver(LeaderboardDataCacheObserver observer);
    void detachObserver(LeaderboardDataCacheObserver observer);
    void notifyObservers(String leaderboardId, LeaderboardData leaderboardData);
}