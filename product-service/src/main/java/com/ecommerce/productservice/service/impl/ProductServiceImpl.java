package com.ecommerce.productservice.service.impl;

import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.integration.queue.ProductProducer;
import com.ecommerce.productservice.integration.queue.mapper.ProductMessageMapper;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.security.CurrentUser;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductProducer productProducer;
    private final ProductMessageMapper productMessageMapper;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductProducer productProducer,
            ProductMessageMapper productMessageMapper
    ) {
        this.productRepository = productRepository;
        this.productProducer = productProducer;
        this.productMessageMapper = productMessageMapper;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        CurrentUser currentUser = CurrentUser.fromContext();

        return productRepository.findAllByUserId(currentUser.getId(), pageable);
    }

    @Override
    public Product getProductById(String id) {
        CurrentUser currentUser = CurrentUser.fromContext();

        return productRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    @Override
    public Product createProduct(Product product) {
        CurrentUser currentUser = CurrentUser.fromContext();

        product.setId(null);
        product.setUserId(currentUser.getId());
        product.setCreatedOn(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        productProducer.sendProductSaveMessage(productMessageMapper.toDto(savedProduct));

        return savedProduct;
    }

    @Override
    public Product updateProduct(String id, Product product) {
        CurrentUser currentUser = CurrentUser.fromContext();

        if (!productRepository.existsByIdAndUserId(id, currentUser.getId())) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }

        product.setId(id);
        Product savedProduct = productRepository.save(product);
        productProducer.sendProductSaveMessage(productMessageMapper.toDto(savedProduct));

        return savedProduct;
    }

    @Override
    public void deleteProductById(String id) {
        CurrentUser currentUser = CurrentUser.fromContext();

        if (!productRepository.existsByIdAndUserId(id, currentUser.getId())) {
            throw new ProductNotFoundException("Product not found with id " + id);
        }

        productRepository.deleteById(id);
    }
}
