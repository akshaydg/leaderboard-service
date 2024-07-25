package com.intuit.craftproject.leaderboardservice.service;

import com.intuit.craftproject.leaderboardservice.dto.LeaderboardRequestDto;
import com.intuit.craftproject.leaderboardservice.dto.LeaderboardResponseDto;
import com.intuit.craftproject.leaderboardservice.model.Leaderboard;

public interface LeaderboardService {

    String createLeaderboard(String leaderboardId, LeaderboardRequestDto leaderboard);

    Leaderboard getLeaderboard(String leaderboardId);

    LeaderboardResponseDto getTopKLeaderboardData(String leaderboardId, int k);
}
