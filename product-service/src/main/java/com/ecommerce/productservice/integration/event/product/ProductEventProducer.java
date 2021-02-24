package com.ecommerce.productservice.integration.event.product;

import com.ecommerce.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${ecommerce.rabbitmq.productsave.exchange}")
    private String productSaveExchange;

    @Value("${ecommerce.rabbitmq.productsave.routingkey}")
    private String productSaveRoutingKey;

    @Value("${ecommerce.rabbitmq.productdelete.exchange}")
    private String productDeleteExchange;

    @Value("${ecommerce.rabbitmq.productdelete.routingkey}")
    private String productDeleteRoutingKey;

    public ProductEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendSavedProductEvent(Product product) {
        log.debug("Sending saved product event with data (data={})", product);

        rabbitTemplate.convertAndSend(productSaveExchange, productSaveRoutingKey, product);
    }

    public void sendDeletedProductEvent(String id) {
        log.debug("Sending deleted product event by id (id={})", id);

        rabbitTemplate.convertAndSend(productDeleteExchange, productDeleteRoutingKey, id);
    }
}
