package com.ecommerce.productservice.integration.queue.mapper;

import com.ecommerce.productservice.integration.queue.dto.ProductMessageDto;
import com.ecommerce.productservice.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ProductMessageMapper {
    public ProductMessageDto toDto(Product product) {
        return new ModelMapper().map(product, ProductMessageDto.class);
    }
}
