package com.ecommerce.productservice.integration.event.product.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private double quantity;
    private double price;
}
