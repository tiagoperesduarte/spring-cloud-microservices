package com.ecommerce.orderservice.integration.event.consumer.product;

public interface DeletedProductEventConsumer {
    void onMessage(String id);
}
