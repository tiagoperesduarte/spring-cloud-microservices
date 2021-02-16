package com.ecommerce.userservice.integration.queue.mapper;

import com.ecommerce.userservice.integration.queue.dto.UserMessageDto;
import com.ecommerce.userservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMessageMapper {
    public UserMessageDto toDto(User user) {
        return new ModelMapper().map(user, UserMessageDto.class);
    }
}
