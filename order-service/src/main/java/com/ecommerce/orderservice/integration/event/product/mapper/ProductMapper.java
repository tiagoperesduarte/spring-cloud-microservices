package com.ecommerce.orderservice.integration.event.product.mapper;

import com.ecommerce.orderservice.integration.event.product.dto.ProductDto;
import com.ecommerce.orderservice.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toModel(ProductDto productDto) {
        return new ModelMapper().map(productDto, Product.class);
    }
}
