package com.intuit.craftproject.leaderboardservice.service.rankingstrategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RankingStrategyConfig {

    @Bean("rankingByUserIdStrategy")
    public RankingStrategy rankingByUserIdStrategy() {
        return new RankingByUserIdStrategy();
    }

    @Bean("rankingByNewerTimestampStrategy")
    public RankingStrategy rankingByNewerTimestampStrategy() {
        return new RankingByNewerTimestampStrategy();
    }
}
