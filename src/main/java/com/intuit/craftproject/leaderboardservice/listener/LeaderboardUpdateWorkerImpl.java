package com.intuit.craftproject.leaderboardservice.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intuit.craftproject.leaderboardservice.model.GameDetails;
import com.intuit.craftproject.leaderboardservice.service.leaderboardcalculationstrategy.LeaderboardCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LeaderboardUpdateWorkerImpl implements LeaderboardUpdateWorker {

    private final LeaderboardCalculationService leaderboardCalculationService;

    private final ThrottledLeaderboardCacheUpdateService throttledLeaderboardCacheUpdateService;

    public LeaderboardUpdateWorkerImpl(LeaderboardCalculationService leaderboardCalculationService, ThrottledLeaderboardCacheUpdateService throttledLeaderboardCacheUpdateService) {
        this.leaderboardCalculationService = leaderboardCalculationService;
        this.throttledLeaderboardCacheUpdateService = throttledLeaderboardCacheUpdateService;
    }


    @Override
    @KafkaListener(id = "leaderboard-service-consumer-1", topics = "user_scores", groupId = "leaderboard-service-consumer-group")
    public void updateLeaderboard(String gameDetailString) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Register the module to handle Java 8 date/time types
        objectMapper.registerModule(new JavaTimeModule());
        // Configure the objectMapper to handle unknown properties
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            GameDetails gameDetails = objectMapper.readValue(gameDetailString, GameDetails.class);
            log.info("Consumed game detailClass: " + gameDetails);
            leaderboardCalculationService.publishScoreToLeaderboard(gameDetails);
            /*
             * Kafka topic storing leaderboard updates to be done later
             */
            throttledLeaderboardCacheUpdateService.throttledLeaderboardCacheUpdater();
        } catch (Exception e) {
            log.error("Error while consuming game details: ", e);
        }
    }
}
