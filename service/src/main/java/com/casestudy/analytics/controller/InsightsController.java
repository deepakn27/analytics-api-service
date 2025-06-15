package com.casestudy.analytics.controller;

import com.casestudy.analytics.dto.response.InsightsResponse;
import com.casestudy.analytics.enums.InsightType;
import com.casestudy.analytics.service.InsightsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ad")
@Slf4j
@AllArgsConstructor
public class InsightsController {

    private final InsightsService insightService;

    @GetMapping("/{campaignId}/clickToBasket")
    public ResponseEntity<InsightsResponse> getClickToBasket(@PathVariable String campaignId) {
        log.info("Fetching click to basket for campaign: {}", campaignId);
        var insightResponse = insightService.getInsights(campaignId, InsightType.CLICK_TO_BASKET);
        return ResponseEntity.ok().body(insightResponse);
    }
}
