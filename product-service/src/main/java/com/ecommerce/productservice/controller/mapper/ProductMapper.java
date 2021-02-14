package com.ecommerce.productservice.controller.mapper;

import com.ecommerce.productservice.controller.dto.ProductDto;
import com.ecommerce.productservice.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {
    public Product toModel(ProductDto productDto) {
        return new ModelMapper().map(productDto, Product.class);
    }

    public ProductDto toDto(Product product) {
        return new ModelMapper().map(product, ProductDto.class);
    }
}
