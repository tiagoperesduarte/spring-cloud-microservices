package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.controller.dto.OrderDto;
import com.ecommerce.orderservice.controller.mapper.OrderMapper;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public Page<OrderDto> getOrders(Pageable pageable) {
        log.debug("Request to find all orders by query (query={})", pageable);

        return orderService.getOrders(pageable)
                .map(orderMapper::toDto);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable String id) {
        log.debug("Request to find order by id (id={})", id);

        return orderMapper.toDto(orderService.getOrderById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@Valid @RequestBody OrderDto orderDto) {
        log.debug("Request to create new order with data (data={})", orderDto);

        Order order = orderService.createOrder(orderMapper.toModel(orderDto));
        return orderMapper.toDto(order);
    }
}
