package com.ecommerce.userservice.integration.queue;


import com.ecommerce.userservice.integration.queue.dto.UserMessageDto;

public interface UserProducer {
    void sendUserSaveMessage(UserMessageDto userMessageDto);

    void sendUserDeleteMessage(String userId);
}
