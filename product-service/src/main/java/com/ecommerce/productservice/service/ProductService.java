package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProductById(String id);

    Product createProduct(Product product);

    Product updateProduct(String id, Product product);

    void deleteProductById(String id);
}
