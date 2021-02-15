package com.ecommerce.orderservice.integration.queue.dto;

import lombok.Data;

@Data
public class ProductMessageDto {
    private String id;
    private double quantity;
    private double price;
}
