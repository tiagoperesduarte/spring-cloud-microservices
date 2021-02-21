package com.ecommerce.productservice.integration.event.producer.product;

import com.ecommerce.productservice.model.Product;

public interface SavedProductEventProducer {
    void sendMessage(Product product);
}
