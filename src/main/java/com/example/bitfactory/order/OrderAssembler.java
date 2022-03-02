package com.example.bitfactory.order;

import com.example.bitfactory.order.command.CreateOrderCommand;
import com.example.bitfactory.order.command.UpdateOrderCommand;
import com.example.bitfactory.order.model.Order;
import com.example.bitfactory.order.representation.OrderRepresentation;
import com.example.bitfactory.order.representation.OrdersRepresentation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderAssembler {
    public static Order toOrder(CreateOrderCommand command, BigDecimal totalPrice) {
        return Order.builder()
                .productId(command.getProductId())
                .num(command.getNum())
                .totalPrice(totalPrice)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static Order toOrder(UpdateOrderCommand command, BigDecimal totalPrice) {
        return Order.builder()
                .num(command.getNum())
                .totalPrice(totalPrice)
                .build();
    }

    public static OrdersRepresentation.OrderVO toOrderVO(Order order) {
        return OrdersRepresentation.OrderVO.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .num(order.getNum())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static OrdersRepresentation toOrdersRepresentation(List<Order> orders) {
        return OrdersRepresentation.builder()
                .orders(orders.stream()
                        .map(OrderAssembler::toOrderVO)
                        .collect(toList()))
                .build();
    }

    public static OrderRepresentation toOrderRepresentation(Order order) {
        return OrderRepresentation.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .num(order.getNum())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
