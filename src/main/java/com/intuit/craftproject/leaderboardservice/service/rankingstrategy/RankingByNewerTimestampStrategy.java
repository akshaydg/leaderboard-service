package com.intuit.craftproject.leaderboardservice.service.rankingstrategy;

import com.intuit.craftproject.leaderboardservice.model.GameDetails;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class RankingByNewerTimestampStrategy implements RankingStrategy {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public int compare(GameDetails a, GameDetails b) {
        try {
            LocalDateTime dateTimeA = LocalDateTime.parse(a.date(), formatter);
            LocalDateTime dateTimeB = LocalDateTime.parse(b.date(), formatter);
            return dateTimeA.compareTo(dateTimeB);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format encountered: ", e);
            // Decide on a fallback comparison, for example, compare user IDs
            return a.userId().compareTo(b.userId());
        }
    }
}