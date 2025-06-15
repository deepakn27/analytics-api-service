package com.casestudy.models.entity.aggregations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableMongoAuditing
@Document(collection="cart_to_basket_insights")
public class ClickToBasketInsights {
    private String campaignId;
    private Long clickToBasketCount;
}
