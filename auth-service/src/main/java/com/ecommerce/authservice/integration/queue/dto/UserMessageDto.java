package com.ecommerce.authservice.integration.queue.dto;

import lombok.Data;

@Data
public class UserMessageDto {
    private String id;
    private String email;
    private String password;
}
