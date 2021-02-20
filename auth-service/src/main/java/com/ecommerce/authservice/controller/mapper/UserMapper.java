package com.ecommerce.authservice.controller.mapper;

import com.ecommerce.authservice.controller.dto.UserDto;
import com.ecommerce.authservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public User toModel(UserDto userDto) {
        return new ModelMapper().map(userDto, User.class);
    }

    public UserDto toDto(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }
}
