package com.ecommerce.productservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String userId;
    private String name;
    private String description;
    private double quantity;
    private double price;
    private LocalDateTime createdOn;
}
