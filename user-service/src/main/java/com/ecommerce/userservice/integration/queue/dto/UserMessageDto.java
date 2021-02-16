package com.ecommerce.userservice.integration.queue.dto;

import lombok.Data;

@Data
public class UserMessageDto {
    private String id;
    private String email;
    private String password;
}
