package com.ecommerce.orderservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private List<OrderItem> items;
    private String comment;
    private double total;
    private Date createdOn;
}
