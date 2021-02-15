package com.ecommerce.orderservice.integration.queue;

import com.ecommerce.orderservice.integration.queue.dto.ProductMessageDto;

public interface ProductConsumer {
    void receiveProductSaveMessage(ProductMessageDto productMessageDto);
}
