package com.casestudy.analytics.service;

import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.models.entity.aggregations.AdInsights;
import com.casestudy.models.entity.aggregations.ClickToBasketInsights;
import org.springframework.stereotype.Service;

/**
 * Converts cached insight values to InsightsResponse objects.
 * This service is responsible for converting different types of insights
 * into a standardized response format.
 */
@Service
public class InsightResponseConverter {

    public InsightsResponse convert(Object cachedValue, InsightType insightType) {
        return switch (insightType) {
            case CLICK_TO_BASKET -> convertClickToBasket((ClickToBasketInsights) cachedValue);
            case AD_VIEW_COUNT -> convertAdView((AdInsights) cachedValue);
            case AD_CLICK_COUNT -> convertAdClick((AdInsights) cachedValue);
        };
    }

    private InsightsResponse convertClickToBasket(ClickToBasketInsights insights) {
        return InsightsResponse.builder()
                .campaignId(insights.getCampaignId())
                .clickToBasketCount(insights.getClickToBasketCount())
                .build();
    }

    private InsightsResponse convertAdView(AdInsights insights) {
        return InsightsResponse.builder()
                .campaignId(insights.getCampaignId())
                .viewCount(insights.getViewCount())
                .build();
    }

    private InsightsResponse convertAdClick(AdInsights insights) {
        return InsightsResponse.builder()
                .campaignId(insights.getCampaignId())
                .clickCount(insights.getClickCount())
                .build();
    }
} 