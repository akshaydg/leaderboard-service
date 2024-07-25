package com.intuit.craftproject.leaderboardservice.model;

public record ScoreCard(String userId, int score) {
    public static ScoreCard fromGameDetails(GameDetails gameDetails) {
        return new ScoreCard(gameDetails.userId(), gameDetails.score());
    }
}