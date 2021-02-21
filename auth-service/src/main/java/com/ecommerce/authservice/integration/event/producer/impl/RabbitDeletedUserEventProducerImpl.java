package com.ecommerce.authservice.integration.event.producer.impl;

import com.ecommerce.authservice.integration.event.producer.DeletedUserEventProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitDeletedUserEventProducerImpl implements DeletedUserEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${ecommerce.rabbitmq.userdelete.exchange}")
    private String userDeleteExchange;

    @Value("${ecommerce.rabbitmq.userdelete.routingkey}")
    private String userDeleteRoutingKey;

    public RabbitDeletedUserEventProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String id) {
        log.debug("Sending deleted user event by id (id={})", id);

        rabbitTemplate.convertAndSend(userDeleteExchange, userDeleteRoutingKey, id);
    }
}
