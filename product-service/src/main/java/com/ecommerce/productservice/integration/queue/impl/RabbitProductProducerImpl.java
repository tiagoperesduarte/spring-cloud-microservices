package com.ecommerce.productservice.integration.queue.impl;

import com.ecommerce.productservice.integration.queue.ProductProducer;
import com.ecommerce.productservice.integration.queue.dto.ProductMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitProductProducerImpl implements ProductProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${ecommerce.rabbitmq.exchange}")
    private String exchange;

    @Value("${ecommerce.rabbitmq.productsave.routingkey}")
    private String productSaveRoutingKey;

    @Value("${ecommerce.rabbitmq.productdelete.routingkey}")
    private String productDeleteRoutingKey;

    public RabbitProductProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendProductSaveMessage(ProductMessageDto productMessageDto) {
        log.debug("Sending product save message with data (data={})", productMessageDto);

        rabbitTemplate.convertAndSend(exchange, productSaveRoutingKey, productMessageDto);
    }

    @Override
    public void sendProductDeleteMessage(String productId) {
        log.debug("Sending product delete message by product_id (product_id={})", productId);

        rabbitTemplate.convertAndSend(exchange, productDeleteRoutingKey, productId);
    }
}
