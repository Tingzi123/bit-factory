package com.example.bitfactory.order.representation;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
@Getter
public class OrdersRepresentation {
    private List<OrdersRepresentation.OrderVO> orders;

    @Builder
    @Getter
    public static class OrderVO {
        private Long id;
        private Long productId;
        private Long num;
        private BigDecimal totalPrice;
        private Instant createdAt;
        private Instant updatedAt;
    }
}
