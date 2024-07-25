package com.intuit.craftproject.leaderboardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardRequestDto {
    private String leaderboardName;
    private String leaderboardType;
}
