package com.example.bitfactory.order;

import com.example.bitfactory.infrastructure.configuration.RedisDao;
import com.example.bitfactory.infrastructure.configuration.RedissonConfig;
import com.example.bitfactory.infrastructure.exception.NotFoundException;
import com.example.bitfactory.order.command.CreateOrderCommand;
import com.example.bitfactory.order.command.UpdateOrderCommand;
import com.example.bitfactory.order.representation.OrderRepresentation;
import com.example.bitfactory.order.representation.OrdersRepresentation;
import com.example.bitfactory.product.ProductApplicationService;
import com.example.bitfactory.product.representation.ProductRepresentation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;

@Service
public class OrderApplicationService {
    private final OrderRepository orderRepository;
    private final ProductApplicationService productApplicationService;
    private final RedisDao redisDao;
    private final RedissonClient redisson;

    public OrderApplicationService(OrderRepository orderRepository,
                                   ProductApplicationService productApplicationService,
                                   RedisDao redisDao) {
        this.orderRepository = orderRepository;
        this.productApplicationService = productApplicationService;
        this.redisDao = redisDao;
        this.redisson = new RedissonConfig().getRedisson();
    }

    @Transactional
    public void createOrder(CreateOrderCommand command) {
        RLock lock = redisson.getLock("orderLock");
        lock.lock();
        ProductRepresentation productRepresentation = productApplicationService.getProductById(command.getProductId());

        var stockString = redisDao.getValue("PRODUCT:" + productRepresentation.getId());
        Long stock;
        if (Objects.isNull(stockString)) {
            redisDao.setKey("PRODUCT:" + productRepresentation.getId(), String.valueOf(productRepresentation.getStock()));
            stock = productRepresentation.getStock();
        } else {
            stock = Long.valueOf(stockString);
        }

        if (stock < command.getNum()) {
            throw new RuntimeException("Out of stock error");
        }
        redisDao.setKey("PRODUCT:" + productRepresentation.getId(), String.valueOf(productRepresentation.getStock() - command.getNum()));
        productApplicationService.updateProductStock(command.getProductId(), command.getNum());
        var totalPrice = productRepresentation.getPrice().multiply(BigDecimal.valueOf(command.getNum()));
        orderRepository.save(OrderAssembler.toOrder(command, totalPrice));
    }

    public OrdersRepresentation getAllOrder() {
        return OrderAssembler.toOrdersRepresentation(orderRepository.findAll());
    }

    public OrderRepresentation getOrderById(Long id) {
        return OrderAssembler.toOrderRepresentation(orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order is not found!")));
    }

    @Transactional
    public void updateOrder(Long id, UpdateOrderCommand command) {
        var order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("order is not found!"));
        ProductRepresentation productRepresentation = productApplicationService.getProductById(order.getProductId());
        var stockString = redisDao.getValue("PRODUCT:" + order.getProductId());
        Long stock;
        if (Objects.isNull(stockString)) {
            redisDao.setKey("PRODUCT:" + order.getProductId(), String.valueOf(productRepresentation.getStock()));
            stock = productRepresentation.getStock();
        } else {
            stock = Long.valueOf(stockString);
        }

        if (stock < command.getNum()) {
            throw new RuntimeException("Out of stock error");
        }
        redisDao.setKey("PRODUCT:" + productRepresentation.getId(), String.valueOf(productRepresentation.getStock() - command.getNum()));

        productApplicationService.updateProductStock(order.getProductId(), command.getNum());
        var totalPrice = productRepresentation.getPrice().multiply(BigDecimal.valueOf(command.getNum()));
        order.update(OrderAssembler.toOrder(command, totalPrice));
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!"));
        orderRepository.delete(order);
    }
}
