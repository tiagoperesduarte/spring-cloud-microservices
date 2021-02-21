package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> getOrders(Pageable pageable);

    Order getOrderById(String id);

    Order createOrder(Order order);

    void deleteOrdersByUserId(String userId);
}
