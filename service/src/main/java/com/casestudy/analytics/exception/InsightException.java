package com.casestudy.analytics.exception;

public class InsightException extends RuntimeException {
    public InsightException(String message) {
        super(message);
    }

    public InsightException(String message, Throwable cause) {
        super(message, cause);
    }
} 