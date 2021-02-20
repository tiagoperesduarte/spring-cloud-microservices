package com.ecommerce.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String userId;
    private ShippingAddress shippingAddress;
    private List<OrderItem> items;
    private double total;
    private OrderStatus status;
    private LocalDateTime createdOn;
}
