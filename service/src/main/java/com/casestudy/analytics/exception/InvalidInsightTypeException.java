package com.casestudy.analytics.exception;

public class InvalidInsightTypeException extends InsightException {
    public InvalidInsightTypeException(String insightType) {
        super(String.format("Invalid insight type: %s", insightType));
    }
} 