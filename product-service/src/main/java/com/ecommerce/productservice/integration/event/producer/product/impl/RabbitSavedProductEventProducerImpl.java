package com.ecommerce.productservice.integration.event.producer.product.impl;

import com.ecommerce.productservice.integration.event.producer.product.SavedProductEventProducer;
import com.ecommerce.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitSavedProductEventProducerImpl implements SavedProductEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${ecommerce.rabbitmq.productsave.exchange}")
    private String productSaveExchange;

    @Value("${ecommerce.rabbitmq.productsave.routingkey}")
    private String productSaveRoutingKey;

    public RabbitSavedProductEventProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Product product) {
        log.debug("Sending saved product event with data (data={})", product);

        rabbitTemplate.convertAndSend(productSaveExchange, productSaveRoutingKey, product);
    }
}
