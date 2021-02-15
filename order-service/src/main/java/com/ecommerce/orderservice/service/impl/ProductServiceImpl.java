package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.model.Product;
import com.ecommerce.orderservice.repository.ProductRepository;
import com.ecommerce.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
