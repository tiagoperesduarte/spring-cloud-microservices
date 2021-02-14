package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.controller.dto.ProductDto;
import com.ecommerce.productservice.controller.mapper.ProductMapper;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public Page<ProductDto> getProducts(Pageable pageable) {
        log.debug("Request to find all products by query (query={})", pageable);

        return productService.getProducts(pageable)
                .map(productMapper::toDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable String id) {
        log.debug("Request to find product by id (id={})", id);

        return productMapper.toDto(productService.getProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        log.debug("Request to create new product with data (data={})", productDto);

        Product product = productService.createProduct(productMapper.toModel(productDto));
        return productMapper.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable String id, @Valid @RequestBody ProductDto productDto) {
        log.debug("Request to update product by id (id={}) with data (data={})", id, productDto);

        Product product = productService.updateProduct(id, productMapper.toModel(productDto));
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        log.debug("Request to delete product by id (id={})", id);

        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
