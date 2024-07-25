package com.intuit.craftproject.leaderboardservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


//postgresql
@Data
@AllArgsConstructor
public class Leaderboard {
    private final String leaderboardId;
    private final String leaderboardName;
    private Date createdAt;
    private LeaderboardTypeEnum leaderboardTypeEnum;
}
