package com.casestudy.analytics.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InsightsResponse {
    public String campaignId;
    public Long clickToBasketCount;
    public Long viewCount;
    public Long clickCount;
}
