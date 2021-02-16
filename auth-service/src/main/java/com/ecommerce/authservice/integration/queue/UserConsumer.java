package com.ecommerce.authservice.integration.queue;

import com.ecommerce.authservice.integration.queue.dto.UserMessageDto;

public interface UserConsumer {
    void receiveUserSaveMessage(UserMessageDto userMessageDto);
}
