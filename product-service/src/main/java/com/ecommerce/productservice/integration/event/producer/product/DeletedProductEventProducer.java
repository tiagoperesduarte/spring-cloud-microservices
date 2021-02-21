package com.ecommerce.productservice.integration.event.producer.product;

public interface DeletedProductEventProducer {
    void sendMessage(String id);
}
