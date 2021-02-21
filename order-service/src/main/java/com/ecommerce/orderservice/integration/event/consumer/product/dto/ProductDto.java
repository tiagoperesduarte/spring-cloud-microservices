package com.ecommerce.orderservice.integration.event.consumer.product.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String userId;
    private double quantity;
    private double price;
}
