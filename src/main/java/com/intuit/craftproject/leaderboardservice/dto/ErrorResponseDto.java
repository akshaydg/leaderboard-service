package com.intuit.craftproject.leaderboardservice.dto;

public record ErrorResponseDto(int statusCode, String message, String details) {
}
