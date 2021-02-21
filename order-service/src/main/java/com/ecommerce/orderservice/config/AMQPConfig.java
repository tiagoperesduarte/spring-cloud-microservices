package com.ecommerce.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {
    @Value("${ecommerce.rabbitmq.productsave.exchange}")
    private String productSaveExchange;

    @Value("${ecommerce.rabbitmq.productsave.routingkey}")
    private String productSaveRoutingKey;

    @Value("${ecommerce.rabbitmq.productsave.queue}")
    private String productSaveQueue;

    @Value("${ecommerce.rabbitmq.productdelete.exchange}")
    private String productDeleteExchange;

    @Value("${ecommerce.rabbitmq.productdelete.routingkey}")
    private String productDeleteRoutingKey;

    @Value("${ecommerce.rabbitmq.productdelete.queue}")
    private String productDeleteQueue;

    @Value("${ecommerce.rabbitmq.userdelete.exchange}")
    private String userDeleteExchange;

    @Value("${ecommerce.rabbitmq.userdelete.routingkey}")
    private String userDeleteRoutingKey;

    @Value("${ecommerce.rabbitmq.userdelete.queue}")
    private String userDeleteQueue;

    @Bean
    public Exchange productSaveExchange() {
        return ExchangeBuilder
                .directExchange(productSaveExchange)
                .durable(true)
                .build();
    }

    @Bean
    public Queue productSaveQueue() {
        return QueueBuilder
                .durable(productSaveQueue)
                .build();
    }

    @Bean
    public Binding productSaveBinding(
            @Qualifier("productSaveExchange") Exchange exchange,
            @Qualifier("productSaveQueue") Queue queue
    ) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(productSaveRoutingKey)
                .noargs();
    }

    @Bean
    public Exchange productDeleteExchange() {
        return ExchangeBuilder
                .directExchange(productDeleteExchange)
                .durable(true)
                .build();
    }

    @Bean
    public Queue productDeleteQueue() {
        return QueueBuilder
                .durable(productDeleteQueue)
                .build();
    }

    @Bean
    public Binding productDeleteBinding(
            @Qualifier("productDeleteExchange") Exchange exchange,
            @Qualifier("productDeleteQueue") Queue queue
    ) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(productDeleteRoutingKey)
                .noargs();
    }

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
