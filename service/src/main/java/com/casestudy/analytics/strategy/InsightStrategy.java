package com.casestudy.analytics.strategy;

public interface InsightStrategy<T> {
    /**
     * Retrieves insights for a given campaign ID.
     *
     * @param campaignId the ID of the campaign
     * @return the insights for the specified campaign
     */
    T getInsights(String campaignId);
} 