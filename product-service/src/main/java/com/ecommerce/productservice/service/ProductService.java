package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getProducts(Pageable pageable);

    Product getProductById(String id);

    Product createProduct(Product product);

    Product updateProduct(String id, Product product);

    void deleteProductById(String id);

    void deleteProductsByUserId(String userId);
}
