package com.example.bitfactory.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long num;
    private BigDecimal totalPrice;
    private Instant createdAt;
    private Instant updatedAt;

    public void update(Order order) {
        this.num = order.num;
        this.totalPrice = order.getTotalPrice();
        this.updatedAt = Instant.now();
    }
}
