package com.ecommerce.orderservice.integration.event.consumer.product.impl;

import com.ecommerce.orderservice.integration.event.consumer.product.SavedProductEventConsumer;
import com.ecommerce.orderservice.integration.event.consumer.product.dto.ProductDto;
import com.ecommerce.orderservice.integration.event.consumer.product.mapper.ProductMapper;
import com.ecommerce.orderservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitSavedProductEventConsumerImpl implements SavedProductEventConsumer {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public RabbitSavedProductEventConsumerImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.productsave.queue}"})
    @Override
    public void onMessage(@Payload ProductDto productDto) {
        log.debug("Receiving saved product event with data (data={})", productDto);

        productService.saveProduct(productMapper.toModel(productDto));
    }
}
