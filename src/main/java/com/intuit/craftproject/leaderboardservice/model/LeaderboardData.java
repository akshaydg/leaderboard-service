package com.intuit.craftproject.leaderboardservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

//redis
@Data
@AllArgsConstructor
public class LeaderboardData {
    private List<ScoreCard> scoreCardList;
}
