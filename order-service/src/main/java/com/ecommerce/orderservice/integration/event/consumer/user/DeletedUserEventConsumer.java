package com.ecommerce.orderservice.integration.event.consumer.user;

public interface DeletedUserEventConsumer {
    void onMessage(String id);
}
