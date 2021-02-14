package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    @Override
    public Order createOrder(Order order) {
        order.setId(null);
        return orderRepository.save(order);
    }
}
