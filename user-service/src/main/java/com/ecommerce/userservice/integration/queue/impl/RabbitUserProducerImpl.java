package com.ecommerce.userservice.integration.queue.impl;

import com.ecommerce.userservice.integration.queue.UserProducer;
import com.ecommerce.userservice.integration.queue.dto.UserMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitUserProducerImpl implements UserProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${ecommerce.rabbitmq.exchange}")
    private String exchange;

    @Value("${ecommerce.rabbitmq.usersave.routingkey}")
    private String userSaveRoutingKey;

    @Value("${ecommerce.rabbitmq.userdelete.routingkey}")
    private String userDeleteRoutingKey;

    public RabbitUserProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendUserSaveMessage(UserMessageDto userMessageDto) {
        log.debug("Sending user save message with data (data={})", userMessageDto);

        rabbitTemplate.convertAndSend(exchange, userSaveRoutingKey, userMessageDto);
    }

    @Override
    public void sendUserDeleteMessage(String userId) {
        log.debug("Sending user delete message by user_id (user_id={})", userId);

        rabbitTemplate.convertAndSend(exchange, userDeleteRoutingKey, userId);
    }
}
