package com.casestudy.analytics.repository;

public interface InsightRepository {

    /**
     * Retrieves insights for a given campaign ID.
     *
     * @param campaignId the ID of the campaign
     * @param insightType the type of insight to retrieve
     * @param <T> the type of the insight
     * @return the insights for the specified campaign and type
     */
    <T> T getInsights(String campaignId, Class<T> insightType);
}
