package com.ecommerce.authservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {
    @Value("${ecommerce.rabbitmq.exchange}")
    private String exchange;

    @Value("${ecommerce.rabbitmq.usersave.routingkey}")
    private String userSaveRoutingKey;

    @Value("${ecommerce.rabbitmq.usersave.queue}")
    private String userSaveQueue;

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder
                .directExchange(exchange)
                .durable(true)
                .build();
    }

    @Bean
    public Queue declareQueue() {
        return QueueBuilder
                .durable(userSaveQueue)
                .build();
    }

    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(userSaveRoutingKey)
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
