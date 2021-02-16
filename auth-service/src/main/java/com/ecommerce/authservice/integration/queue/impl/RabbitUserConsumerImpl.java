package com.ecommerce.authservice.integration.queue.impl;

import com.ecommerce.authservice.integration.queue.UserConsumer;
import com.ecommerce.authservice.integration.queue.dto.UserMessageDto;
import com.ecommerce.authservice.integration.queue.mapper.UserMessageMapper;
import com.ecommerce.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitUserConsumerImpl implements UserConsumer {
    private final UserService userService;
    private final UserMessageMapper userMessageMapper;

    @Autowired
    public RabbitUserConsumerImpl(UserService userService, UserMessageMapper userMessageMapper) {
        this.userService = userService;
        this.userMessageMapper = userMessageMapper;
    }

    @RabbitListener(queues = {"${ecommerce.rabbitmq.usersave.queue}"})
    @Override
    public void receiveUserSaveMessage(@Payload UserMessageDto userMessageDto) {
        log.debug("Receiving user save message with data (data={})", userMessageDto);

        userService.saveUser(userMessageMapper.toModel(userMessageDto));
    }
}
