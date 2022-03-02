package com.example.bitfactory.product.representation;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
@Getter
public class ProductsRepresentation {
    private List<ProductVO> products;

    @Builder
    @Getter
    public static class ProductVO {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private String link;
        private Long stock;
        private Instant createdAt;
        private Instant updatedAt;
    }
}
