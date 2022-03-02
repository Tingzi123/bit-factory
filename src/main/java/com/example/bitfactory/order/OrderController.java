package com.example.bitfactory.order;

import com.example.bitfactory.infrastructure.Resource;
import com.example.bitfactory.order.command.CreateOrderCommand;
import com.example.bitfactory.order.command.UpdateOrderCommand;
import com.example.bitfactory.order.representation.OrderRepresentation;
import com.example.bitfactory.order.representation.OrdersRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping
    public Resource<Void> createOrder(@RequestBody @Valid CreateOrderCommand command) {
        orderApplicationService.createOrder(command);
        return Resource.empty();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping
    public Resource<OrdersRepresentation> getAllOrder() {
        return Resource.of(orderApplicationService.getAllOrder());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public Resource<OrderRepresentation> getOrderById(@PathVariable Long id) {
        return Resource.of(orderApplicationService.getOrderById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Resource<Void> updateOrder(@PathVariable Long id, @Valid @RequestBody UpdateOrderCommand command) {
        orderApplicationService.updateOrder(id, command);
        return Resource.empty();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public Resource<Void> deleteOrder(@PathVariable Long id) {
        orderApplicationService.deleteOrder(id);
        return Resource.empty();
    }
}
