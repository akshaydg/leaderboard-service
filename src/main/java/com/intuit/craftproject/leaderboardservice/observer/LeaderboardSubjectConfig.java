package com.intuit.craftproject.leaderboardservice.observer;

import com.intuit.craftproject.leaderboardservice.repository.InMemoryAllTimeLeaderboardDataCacheObserver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeaderboardSubjectConfig {

    @Bean
    public LeaderboardSubject leaderboardSubject(InMemoryAllTimeLeaderboardDataCacheObserver inMemoryAllTimeLeaderboardDataCacheObserver) {
        LeaderboardSubject leaderboardSubject = new LeaderboardSubjectImpl();
        leaderboardSubject.attachObserver(inMemoryAllTimeLeaderboardDataCacheObserver);
        return leaderboardSubject;
    }
}
