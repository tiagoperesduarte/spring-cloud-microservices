package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.exception.ProductNotFoundException;
import com.ecommerce.orderservice.model.Product;
import com.ecommerce.orderservice.repository.ProductRepository;
import com.ecommerce.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteProductsByUserId(String userId) {
        productRepository.deleteAllByUserId(userId);
    }
}
