package com.ecommerce.orderservice.controller.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private String productId;
    private double quantity;
    private double price;
}
