package com.intuit.craftproject.leaderboardservice.listener;

import com.intuit.craftproject.leaderboardservice.model.LeaderboardData;
import com.intuit.craftproject.leaderboardservice.observer.LeaderboardSubject;
import com.intuit.craftproject.leaderboardservice.service.leaderboardcalculationstrategy.LeaderboardCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class ThrottledLeaderboardCacheUpdateServiceImpl implements ThrottledLeaderboardCacheUpdateService{

    @Value("${leaderboard.all-time.id}")
    private String allTimeLeaderboardId;

    @Value("${leaderboard.max-size}")
    private int maxSize;

    private final AtomicLong lastExecutionTime = new AtomicLong(0);

    private final LeaderboardSubject leaderboardSubjectImpl;

    private final LeaderboardCalculationService leaderboardCalculationService;


    public ThrottledLeaderboardCacheUpdateServiceImpl(LeaderboardSubject leaderboardSubjectImpl, LeaderboardCalculationService leaderboardCalculationService) {
        this.leaderboardSubjectImpl = leaderboardSubjectImpl;
        this.leaderboardCalculationService = leaderboardCalculationService;
    }


    @Async
    @Override
    public void throttledLeaderboardCacheUpdater() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastExecutionTime.get() >= 500) {
            if (lastExecutionTime.compareAndSet(lastExecutionTime.get(), currentTime)) {
                leaderboardSubjectImpl.notifyObservers(allTimeLeaderboardId, new LeaderboardData(leaderboardCalculationService.getTopKLeaderboard(maxSize)));
            }
        } else {
            log.info("Method call skipped, waiting for 500ms to pass.");
        }
    }

}
