package com.intuit.craftproject.leaderboardservice.exception;

public class InvalidLeaderboardException extends RuntimeException {
    public InvalidLeaderboardException(String message) {
        super(message);
    }
}