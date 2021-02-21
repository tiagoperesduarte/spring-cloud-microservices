package com.ecommerce.productservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {
    @Value("${ecommerce.rabbitmq.userdelete.exchange}")
    private String userDeleteExchange;

    @Value("${ecommerce.rabbitmq.userdelete.routingkey}")
    private String userDeleteRoutingKey;

    @Value("${ecommerce.rabbitmq.userdelete.queue}")
    private String userDeleteQueue;

    @Bean
    public Exchange userDeleteExchange() {
        return ExchangeBuilder
                .directExchange(userDeleteExchange)
                .durable(true)
                .build();
    }

    @Bean
    public Queue userDeleteQueue() {
        return QueueBuilder
                .durable(userDeleteQueue)
                .build();
    }

    @Bean
    public Binding userDeleteBinding(
            @Qualifier("userDeleteExchange") Exchange exchange,
            @Qualifier("userDeleteQueue") Queue queue
    ) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(userDeleteRoutingKey)
                .noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
