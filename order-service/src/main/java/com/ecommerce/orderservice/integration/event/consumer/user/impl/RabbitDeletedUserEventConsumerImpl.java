package com.ecommerce.orderservice.integration.event.consumer.user.impl;

import com.ecommerce.orderservice.integration.event.consumer.user.DeletedUserEventConsumer;
import com.ecommerce.orderservice.service.OrderService;
import com.ecommerce.orderservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitDeletedUserEventConsumerImpl implements DeletedUserEventConsumer {
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public RabbitDeletedUserEventConsumerImpl(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.userdelete.queue}"})
    @Override
    public void onMessage(@Payload String id) {
        log.debug("Receiving deleted user event with id (id={})", id);

        orderService.deleteOrdersByUserId(id);
        productService.deleteProductsByUserId(id);
    }
}
