package com.ecommerce.userservice.controller.mapper;

import com.ecommerce.userservice.controller.dto.UserDto;
import com.ecommerce.userservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public User toModel(UserDto productDto) {
        return new ModelMapper().map(productDto, User.class);
    }

    public UserDto toDto(User product) {
        return new ModelMapper().map(product, UserDto.class);
    }
}
