package com.casestudy.models.entity.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    private String adId;
    private String campaignId;
    private String productId;
    private Instant timestamp;
    private String adPlacement;
}
