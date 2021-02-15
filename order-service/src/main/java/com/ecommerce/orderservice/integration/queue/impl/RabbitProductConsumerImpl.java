package com.ecommerce.orderservice.integration.queue.impl;

import com.ecommerce.orderservice.integration.queue.ProductConsumer;
import com.ecommerce.orderservice.integration.queue.dto.ProductMessageDto;
import com.ecommerce.orderservice.integration.queue.mapper.ProductMessageMapper;
import com.ecommerce.orderservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitProductConsumerImpl implements ProductConsumer {
    private final ProductService productService;
    private final ProductMessageMapper productMessageMapper;

    @Autowired
    public RabbitProductConsumerImpl(ProductService productService, ProductMessageMapper productMessageMapper) {
        this.productService = productService;
        this.productMessageMapper = productMessageMapper;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.productsave.queue}"})
    @Override
    public void receiveProductSaveMessage(@Payload ProductMessageDto productMessageDto) {
        log.debug("Receiving product save message with data (data={})", productMessageDto);

        productService.saveProduct(productMessageMapper.toModel(productMessageDto));
    }
}
