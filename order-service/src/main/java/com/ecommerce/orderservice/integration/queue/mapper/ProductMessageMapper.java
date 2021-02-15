package com.ecommerce.orderservice.integration.queue.mapper;

import com.ecommerce.orderservice.integration.queue.dto.ProductMessageDto;
import com.ecommerce.orderservice.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ProductMessageMapper {
    public Product toModel(ProductMessageDto productMessageDto) {
        return new ModelMapper().map(productMessageDto, Product.class);
    }
}
