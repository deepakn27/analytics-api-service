package com.casestudy.models.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String cartId;
    private String userId;
    private String productId;
    private Instant timestamp;
    private int quantity;
}
