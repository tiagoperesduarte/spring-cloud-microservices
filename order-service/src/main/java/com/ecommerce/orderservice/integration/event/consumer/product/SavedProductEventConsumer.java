package com.ecommerce.orderservice.integration.event.consumer.product;

import com.ecommerce.orderservice.integration.event.consumer.product.dto.ProductDto;

public interface SavedProductEventConsumer {
    void onMessage(ProductDto productDto);
}
