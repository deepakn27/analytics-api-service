package com.casestudy.analytics.strategy.impl;

import com.casestudy.analytics.annotation.InsightSelector;
import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.analytics.exception.InsightNotFoundException;
import com.casestudy.analytics.repository.InsightRepository;
import com.casestudy.analytics.repository.MongoRepository;
import com.casestudy.analytics.strategy.InsightStrategy;
import com.casestudy.models.entity.aggregations.AdInsights;
import com.casestudy.models.entity.aggregations.ClickToBasketInsights;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.casestudy.analytics.constant.RedisConstant.AD_CLICK_COUNT;
import static com.casestudy.analytics.constant.RedisConstant.CLICK_TO_BASKET_COUNT;

@Component
@RequiredArgsConstructor
@InsightSelector(InsightType.CLICK_TO_BASKET)
public class ClickToBasketStrategy implements InsightStrategy<InsightsResponse> {

    private final InsightRepository insightRepository;

    /**
     * Retrieves insights for the given campaign ID, specifically focusing on click-to-basket counts.
     *
     * @param campaignId the ID of the campaign for which insights are requested
     * @return InsightsResponse containing the click-to-basket count for the specified campaign
     * @throws InsightNotFoundException if no insights are found for the given campaign ID
     */
    @Override
    public InsightsResponse getInsights(String campaignId) {
        ClickToBasketInsights insights = insightRepository.getInsights(campaignId, ClickToBasketInsights.class);
        if (insights == null) {
            throw new InsightNotFoundException(campaignId, InsightType.CLICK_TO_BASKET.name());
        }
        return InsightsResponse.builder()
                .campaignId(campaignId)
                .clickToBasketCount(insights.getClickToBasketCount())
                .build();
    }
} 