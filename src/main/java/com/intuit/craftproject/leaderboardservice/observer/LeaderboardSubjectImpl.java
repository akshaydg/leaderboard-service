// LeaderboardSubjectImpl.java
package com.intuit.craftproject.leaderboardservice.observer;

import com.intuit.craftproject.leaderboardservice.model.LeaderboardData;
import com.intuit.craftproject.leaderboardservice.repository.LeaderboardDataCacheObserver;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardSubjectImpl implements LeaderboardSubject {

    private final List<LeaderboardDataCacheObserver> observers = new ArrayList<>();

    @Override
    public void attachObserver(LeaderboardDataCacheObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(LeaderboardDataCacheObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String leaderboardId, LeaderboardData leaderboardData) {
        for (LeaderboardDataCacheObserver observer : observers) {
            observer.updateLeaderboardData(leaderboardId, leaderboardData);
        }
    }
}