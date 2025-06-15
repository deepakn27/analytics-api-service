package com.casestudy.analytics.service;

import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;

public interface InsightsService {

    /**
     * Retrieves insights for a given campaign ID and insight type.
     *
     * @param campaignId the ID of the campaign
     * @param insightType the type of insight to retrieve
     * @return the insights for the specified campaign and type
     */
    InsightsResponse getInsights(String campaignId, InsightType insightType);
}
