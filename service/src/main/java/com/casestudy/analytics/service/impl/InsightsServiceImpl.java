package com.casestudy.analytics.service.impl;

import com.casestudy.analytics.annotation.CacheableInsight;
import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.analytics.exception.InvalidInsightTypeException;
import com.casestudy.analytics.service.InsightsService;
import com.casestudy.analytics.strategy.InsightStrategy;
import com.casestudy.analytics.factory.InsightStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsightsServiceImpl implements InsightsService {

    private final InsightStrategyFactory strategyFactory;

    /**
     * Retrieves insights for a given campaign ID and insight type.
     *
     * @param campaignId the ID of the campaign for which insights are requested
     * @param insightType the type of insight to retrieve
     * @return InsightsResponse containing the requested insights
     * @throws InvalidInsightTypeException if the provided insight type is not supported
     */
    @CacheableInsight()
    public InsightsResponse getInsights(String campaignId, InsightType insightType) {
        log.info("Fetching insights for campaignId: {} and insightType: {}", campaignId, insightType);
        
        InsightStrategy<InsightsResponse> strategy = strategyFactory.getInsight(insightType);
        if (strategy == null) {
            log.error("No strategy found for insightType: {}", insightType);
            throw new InvalidInsightTypeException(insightType.name());
        }
        
        log.debug("Using strategy: {} for insightType: {}", strategy.getClass().getSimpleName(), insightType);
        InsightsResponse response = strategy.getInsights(campaignId);
        log.info("Successfully retrieved insights for campaignId: {} and insightType: {}", campaignId, insightType);
        
        return response;
    }
}
