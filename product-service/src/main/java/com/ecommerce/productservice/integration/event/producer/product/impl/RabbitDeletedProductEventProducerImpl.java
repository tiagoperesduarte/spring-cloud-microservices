package com.ecommerce.productservice.integration.event.producer.product.impl;

import com.ecommerce.productservice.integration.event.producer.product.DeletedProductEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitDeletedProductEventProducerImpl implements DeletedProductEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${ecommerce.rabbitmq.productdelete.exchange}")
    private String productDeleteExchange;

    @Value("${ecommerce.rabbitmq.productdelete.routingkey}")
    private String productDeleteRoutingKey;

    public RabbitDeletedProductEventProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String id) {
        log.debug("Sending deleted product event by id (id={})", id);

        rabbitTemplate.convertAndSend(productDeleteExchange, productDeleteRoutingKey, id);
    }
}
