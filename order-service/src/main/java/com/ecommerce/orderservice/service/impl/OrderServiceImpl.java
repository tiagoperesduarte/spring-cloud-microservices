package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.exception.OrderNotFoundException;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderStatus;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.security.CurrentUser;
import com.ecommerce.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        CurrentUser currentUser = CurrentUser.fromContext();

        return orderRepository.findAllByUserId(currentUser.getId(), pageable);
    }

    @Override
    public Order getOrderById(String id) {
        CurrentUser currentUser = CurrentUser.fromContext();

        return orderRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
    }

    @Override
    public Order createOrder(Order order) {
        CurrentUser currentUser = CurrentUser.fromContext();

        order.setId(null);
        order.setUserId(currentUser.getId());
        order.setTotal(getOrderTotal(order));
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedOn(LocalDateTime.now());

        return orderRepository.save(order);
    }

    private double getOrderTotal(Order order) {
        return order.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }
}
