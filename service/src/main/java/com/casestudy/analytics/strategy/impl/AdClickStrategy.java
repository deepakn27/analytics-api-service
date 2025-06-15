package com.casestudy.analytics.strategy.impl;

import com.casestudy.analytics.annotation.InsightSelector;
import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.analytics.exception.InsightNotFoundException;
import com.casestudy.analytics.repository.InsightRepository;
import com.casestudy.analytics.repository.MongoRepository;
import com.casestudy.analytics.strategy.InsightStrategy;
import com.casestudy.models.entity.aggregations.AdInsights;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.casestudy.analytics.constant.RedisConstant.AD_CLICK_COUNT;

@Component
@RequiredArgsConstructor
@InsightSelector(InsightType.AD_CLICK_COUNT)
public class AdClickStrategy implements InsightStrategy<InsightsResponse> {
    
    private final InsightRepository insightRepository;

    /**
     * Retrieves insights for the given campaign ID, specifically focusing on ad click counts.
     *
     * @param campaignId the ID of the campaign for which insights are requested
     * @return InsightsResponse containing the click count for the specified campaign
     * @throws InsightNotFoundException if no insights are found for the given campaign ID
     */
    @Override
    public InsightsResponse getInsights(String campaignId) {
        AdInsights insights = insightRepository.getInsights(campaignId, AdInsights.class);
        if (insights == null) {
            throw new InsightNotFoundException(campaignId, InsightType.AD_CLICK_COUNT.name());
        }
        return InsightsResponse.builder()
                .campaignId(campaignId)
                .clickCount(insights.getClickCount())
                .build();
    }

} 