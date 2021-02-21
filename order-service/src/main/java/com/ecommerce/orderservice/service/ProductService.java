package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.Product;

public interface ProductService {
    Product getProductById(String id);

    Product saveProduct(Product product);

    void deleteProductById(String id);

    void deleteProductsByUserId(String userId);
}
