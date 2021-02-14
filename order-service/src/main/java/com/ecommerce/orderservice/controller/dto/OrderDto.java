package com.ecommerce.orderservice.controller.dto;

import com.ecommerce.orderservice.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private String id;
    private List<OrderItemDto> items;
    private double total;
    private OrderStatus status;
    private LocalDateTime createdOn;
}
