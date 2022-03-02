package com.example.bitfactory.product.representation;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
public class ProductRepresentation {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String link;
    private Long stock;
    private Instant createdAt;
    private Instant updatedAt;
}
