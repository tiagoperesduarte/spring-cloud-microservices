package com.ecommerce.orderservice.controller.mapper;

import com.ecommerce.orderservice.controller.dto.OrderDto;
import com.ecommerce.orderservice.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper {
    public Order toModel(OrderDto orderDto) {
        return new ModelMapper().map(orderDto, Order.class);
    }

    public OrderDto toDto(Order order) {
        return new ModelMapper().map(order, OrderDto.class);
    }
}
