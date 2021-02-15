package com.ecommerce.productservice.integration.queue;

import com.ecommerce.productservice.integration.queue.dto.ProductMessageDto;

public interface ProductProducer {
    void sendProductSaveMessage(ProductMessageDto productMessageDto);

    void sendProductDeleteMessage(String productId);
}
