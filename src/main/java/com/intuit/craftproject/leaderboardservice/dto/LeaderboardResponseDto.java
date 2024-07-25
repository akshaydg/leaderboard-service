package com.intuit.craftproject.leaderboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LeaderboardResponseDto {
    private final String leaderboardId;
    private List<ScoreCardResponseDto> leaderboard;
}
