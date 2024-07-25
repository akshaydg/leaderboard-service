package com.intuit.craftproject.leaderboardservice.service.leaderboardcalculationstrategy;

import com.intuit.craftproject.leaderboardservice.model.ScoreCard;
import com.intuit.craftproject.leaderboardservice.model.GameDetails;
import com.intuit.craftproject.leaderboardservice.service.rankingstrategy.RankingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AllTimeLeaderboardCalculationServiceTest {

    @Mock
    private RankingStrategy rankingStrategy;

    private AllTimeLeaderboardCalculationService leaderboardCalculationService;

    private static final int MAX_SIZE = 5;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaderboardCalculationService = new AllTimeLeaderboardCalculationService(MAX_SIZE, rankingStrategy);
    }

    @Test
    void testPublishScoreToLeaderboard_AddNewScore() {
        GameDetails gameDetails = new GameDetails("user1", 100, "game1", "2024-07-18T11:10:16+0000");

        leaderboardCalculationService.publishScoreToLeaderboard(gameDetails);

        List<ScoreCard> topKLeaderboard = leaderboardCalculationService.getTopKLeaderboard(MAX_SIZE);

        assertFalse(topKLeaderboard.isEmpty());
        assertEquals(1, topKLeaderboard.size());
        assertEquals(100, topKLeaderboard.get(0).score());
    }

    @Test
    void testPublishScoreToLeaderboard_ReplaceLowestScore() {
        GameDetails lowestScore = new GameDetails("user1", 50, "game1", "2024-07-18T11:10:16+0000");
        leaderboardCalculationService.publishScoreToLeaderboard(lowestScore);

        GameDetails newHighScore = new GameDetails("user2", 100, "game2", "2024-07-18T11:10:16+0000");
        leaderboardCalculationService.publishScoreToLeaderboard(newHighScore);

        List<ScoreCard> topKLeaderboard = leaderboardCalculationService.getTopKLeaderboard(MAX_SIZE);

        assertFalse(topKLeaderboard.isEmpty());
        assertEquals(2, topKLeaderboard.size());
        assertEquals(100, topKLeaderboard.get(0).score());
        assertEquals(50, topKLeaderboard.get(1).score());
    }

    @Test
    void testGetTopKLeaderboard_ValidSize() {
        GameDetails gameDetails1 = new GameDetails("user1", 100, "game1", "2024-07-18T11:10:16+0000");
        GameDetails gameDetails2 = new GameDetails("user2", 90, "game2", "2024-07-18T11:10:16+0000");
        leaderboardCalculationService.publishScoreToLeaderboard(gameDetails1);
        leaderboardCalculationService.publishScoreToLeaderboard(gameDetails2);

        List<ScoreCard> topKLeaderboard = leaderboardCalculationService.getTopKLeaderboard(2);

        assertEquals(2, topKLeaderboard.size());
        assertEquals(100, topKLeaderboard.get(0).score());
        assertEquals(90, topKLeaderboard.get(1).score());
    }

    @Test
    void testGetTopKLeaderboard_SizeGreaterThanMaxSize() {
        assertThrows(IllegalArgumentException.class, () -> leaderboardCalculationService.getTopKLeaderboard(MAX_SIZE + 1));
    }

    @Test
    void testPublishScoreToLeaderboard_EqualScores() {
        GameDetails gameDetails1 = new GameDetails("user1", 100, "game1", "2024-07-18T11:10:16+0000");
        leaderboardCalculationService.publishScoreToLeaderboard(gameDetails1);

        GameDetails gameDetails2 = new GameDetails("user2", 100, "game2", "2024-07-18T11:10:16+0000");
        when(rankingStrategy.compare(gameDetails1, gameDetails2)).thenReturn(-1);

        leaderboardCalculationService.publishScoreToLeaderboard(gameDetails2);

        List<ScoreCard> topKLeaderboard = leaderboardCalculationService.getTopKLeaderboard(MAX_SIZE);

        assertFalse(topKLeaderboard.isEmpty());
        assertEquals(2, topKLeaderboard.size());
        assertEquals("user1", topKLeaderboard.get(0).userId());
        assertEquals("user2", topKLeaderboard.get(1).userId());
    }
}
