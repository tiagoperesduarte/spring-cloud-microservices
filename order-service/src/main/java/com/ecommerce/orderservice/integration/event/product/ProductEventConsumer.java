package com.ecommerce.orderservice.integration.event.product;

import com.ecommerce.orderservice.integration.event.product.dto.ProductDto;
import com.ecommerce.orderservice.integration.event.product.mapper.ProductMapper;
import com.ecommerce.orderservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventConsumer {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductEventConsumer(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.productsave.queue}"})
    public void receiveSavedProductEvent(@Payload ProductDto productDto) {
        log.debug("Receiving saved product event with data (data={})", productDto);

        productService.saveProduct(productMapper.toModel(productDto));
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.productdelete.queue}"})
    public void receiveDeletedProductEvent(@Payload String id) {
        log.debug("Receiving deleted product event with id (id={})", id);

        productService.deleteProductById(id);
    }
}
