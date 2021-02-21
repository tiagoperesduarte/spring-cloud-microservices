package com.ecommerce.orderservice.integration.event.consumer.product.impl;

import com.ecommerce.orderservice.integration.event.consumer.product.DeletedProductEventConsumer;
import com.ecommerce.orderservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitDeletedProductEventConsumerImpl implements DeletedProductEventConsumer {
    private final ProductService productService;

    @Autowired
    public RabbitDeletedProductEventConsumerImpl(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.productdelete.queue}"})
    @Override
    public void onMessage(@Payload String id) {
        log.debug("Receiving deleted product event with id (id={})", id);

        productService.deleteProductById(id);
    }
}
