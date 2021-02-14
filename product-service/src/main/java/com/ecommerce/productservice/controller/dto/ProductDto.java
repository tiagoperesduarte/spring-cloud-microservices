package com.ecommerce.productservice.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private double quantity;
    private double price;
    private LocalDateTime createdOn;
}
