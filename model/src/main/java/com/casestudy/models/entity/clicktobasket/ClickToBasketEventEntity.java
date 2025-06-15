package com.casestudy.models.entity.clicktobasket;

import com.casestudy.models.entity.ad.Advertisement;
import com.casestudy.models.entity.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableMongoAuditing
@Document(collection="click_to_cart_events")
public class ClickToBasketEventEntity {
    private String eventType;
    private String eventId;
    private String tenantId;
    private String userId;
    private String sessionId;
    private Advertisement advertisement;
    private Cart cart;
}
