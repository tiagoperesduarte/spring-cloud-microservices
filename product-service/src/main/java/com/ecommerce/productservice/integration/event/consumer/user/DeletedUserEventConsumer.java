package com.ecommerce.productservice.integration.event.consumer.user;

public interface DeletedUserEventConsumer {
    void onMessage(String id);
}
