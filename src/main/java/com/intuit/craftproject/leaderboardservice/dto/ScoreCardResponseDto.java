package com.intuit.craftproject.leaderboardservice.dto;


public record ScoreCardResponseDto(String userId, String userName, int score) {
}