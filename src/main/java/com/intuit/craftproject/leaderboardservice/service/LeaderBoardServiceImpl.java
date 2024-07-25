package com.intuit.craftproject.leaderboardservice.service;

import com.intuit.craftproject.leaderboardservice.dto.LeaderboardRequestDto;
import com.intuit.craftproject.leaderboardservice.dto.LeaderboardResponseDto;
import com.intuit.craftproject.leaderboardservice.dto.ScoreCardResponseDto;
import com.intuit.craftproject.leaderboardservice.model.Leaderboard;
import com.intuit.craftproject.leaderboardservice.model.LeaderboardTypeEnum;
import com.intuit.craftproject.leaderboardservice.repository.LeaderboardDataCacheObserver;
import com.intuit.craftproject.leaderboardservice.repository.LeaderboardRepository;
import com.intuit.craftproject.leaderboardservice.repository.UserDetailsCacheRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class LeaderBoardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    private final LeaderboardDataCacheObserver leaderboardDataCacheObserver;

    private final UserDetailsCacheRepository userDetailsCacheRepository;

    public LeaderBoardServiceImpl(LeaderboardRepository leaderboardRepository, LeaderboardDataCacheObserver leaderboardDataCacheObserver, UserDetailsCacheRepository userDetailsCacheRepository) {
        this.leaderboardRepository = leaderboardRepository;
        this.leaderboardDataCacheObserver = leaderboardDataCacheObserver;
        this.userDetailsCacheRepository = userDetailsCacheRepository;
    }


    @Override
    public String createLeaderboard(String leaderboardId, LeaderboardRequestDto leaderboardRequestDto) {
        Optional<Leaderboard> optionalLeaderboardResponse = leaderboardRepository.getLeaderboard(leaderboardId);
        if(optionalLeaderboardResponse.isPresent()) return optionalLeaderboardResponse.get().getLeaderboardId();
        return leaderboardRepository.addLeaderboard(leaderboardRequestDto.getLeaderboardName(), new Date(), LeaderboardTypeEnum.valueOf(leaderboardRequestDto.getLeaderboardType()));
    }

    @Override
    public Leaderboard getLeaderboard(String leaderboardId) {
        return leaderboardRepository.getLeaderboard(leaderboardId).orElse(null);
    }

    @Override
    public LeaderboardResponseDto getTopKLeaderboardData(String leaderboardId, int k) {
        return leaderboardDataCacheObserver.getTopKLeaderboardData(leaderboardId)
                .map(data -> new LeaderboardResponseDto(leaderboardId, data.getScoreCardList().stream()
                        .limit(Math.min(k, data.getScoreCardList().size()))
                        .map(obj -> new ScoreCardResponseDto(obj.userId(), userDetailsCacheRepository.getUserName(obj.userId()), obj.score()))
                        .toList()))
                .orElse(new LeaderboardResponseDto(leaderboardId, new ArrayList<>()));
    }
}
