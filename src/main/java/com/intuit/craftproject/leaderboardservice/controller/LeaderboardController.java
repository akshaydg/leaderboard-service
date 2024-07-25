package com.intuit.craftproject.leaderboardservice.controller;

import com.intuit.craftproject.leaderboardservice.dto.LeaderboardRequestDto;
import com.intuit.craftproject.leaderboardservice.dto.LeaderboardResponseDto;
import com.intuit.craftproject.leaderboardservice.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LeaderboardController implements ILeaderboardController{

    private final LeaderboardService leaderBoardService;

    public LeaderboardController(LeaderboardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping("/top/leaderboards/{leaderboardId}")
    public ResponseEntity<?> getTopKLeaderboardData(@PathVariable String leaderboardId, @RequestParam(defaultValue = "5") int size) {
        LeaderboardResponseDto leaderboardResponse = leaderBoardService.getTopKLeaderboardData(leaderboardId, size);
        return ResponseEntity.ok(leaderboardResponse);
    }

    @GetMapping("/leaderboards/{leaderboardId}")
    public ResponseEntity<?> getLeaderboard(@PathVariable String leaderboardId) {
        return ResponseEntity.ok(leaderBoardService.getLeaderboard(leaderboardId));
    }

    @PutMapping("/leaderboards/{leaderboardId}")
    public ResponseEntity<?> createLeaderboard(@PathVariable String leaderboardId, @RequestBody LeaderboardRequestDto leaderboardRequestDto) {
        return ResponseEntity.ok( leaderBoardService.createLeaderboard(leaderboardId, leaderboardRequestDto));
    }
}
