package com.ecommerce.authservice.integration.queue.mapper;

import com.ecommerce.authservice.integration.queue.dto.UserMessageDto;
import com.ecommerce.authservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMessageMapper {
    public User toModel(UserMessageDto userMessageDto) {
        User user = new ModelMapper().map(userMessageDto, User.class);
        user.setUsername(userMessageDto.getEmail());

        return user;
    }
}
