package com.ecommerce.authservice.integration.event.producer;

public interface DeletedUserEventProducer {
    void sendMessage(String id);
}
