package com.casestudy.analytics.exception;

public class InsightNotFoundException extends InsightException {
    public InsightNotFoundException(String campaignId, String insightType) {
        super(String.format("No insights found for campaign %s and type %s", campaignId, insightType));
    }
} 