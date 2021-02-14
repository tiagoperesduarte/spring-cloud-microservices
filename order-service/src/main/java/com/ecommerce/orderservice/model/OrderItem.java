package com.ecommerce.orderservice.model;

import lombok.Data;

@Data
public class OrderItem {
    private String productId;
    private double quantity;
    private double price;
}
