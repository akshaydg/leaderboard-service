package com.intuit.craftproject.leaderboardservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<T> createResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> created(T body) {
        return createResponse(body, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> noContent() {
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    public static <T> ResponseEntity<T> badRequest(T body) {
        return createResponse(body, HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<T> notFound(T body) {
        return createResponse(body, HttpStatus.NOT_FOUND);
    }

    // Additional methods for other statuses as needed
}
