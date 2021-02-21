package com.ecommerce.productservice.integration.event.consumer.user.impl;

import com.ecommerce.productservice.integration.event.consumer.user.DeletedUserEventConsumer;
import com.ecommerce.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitDeletedUserEventConsumerImpl implements DeletedUserEventConsumer {
    private final ProductService productService;

    @Autowired
    public RabbitDeletedUserEventConsumerImpl(ProductService orderService) {
        this.productService = orderService;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.userdelete.queue}"})
    @Override
    public void onMessage(@Payload String id) {
        log.debug("Receiving deleted user event with id (id={})", id);

        productService.deleteProductsByUserId(id);
    }
}
