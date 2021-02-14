package com.ecommerce.orderservice.controller.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private String id;
    private List<OrderItemDto> items;
    private String comment;
    private double total;
    private Date createdOn;
}
