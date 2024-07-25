package com.intuit.craftproject.leaderboardservice.controller;

import com.intuit.craftproject.leaderboardservice.dto.LeaderboardRequestDto;
import org.springframework.http.ResponseEntity;

public interface ILeaderboardController {
    ResponseEntity<?> getTopKLeaderboardData(String leaderboardId, int size);
    ResponseEntity<?> getLeaderboard(String leaderboardId);
    ResponseEntity<?> createLeaderboard(String leaderboardId, LeaderboardRequestDto leaderboardRequestDto);
}
