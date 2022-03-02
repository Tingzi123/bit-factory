package com.example.bitfactory.order.representation;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
public class OrderRepresentation {
    private Long id;
    private Long productId;
    private Long num;
    private BigDecimal totalPrice;
    private Instant createdAt;
    private Instant updatedAt;
}
