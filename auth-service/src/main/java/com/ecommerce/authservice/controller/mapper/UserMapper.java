package com.ecommerce.authservice.controller.mapper;

import com.ecommerce.authservice.controller.dto.UserDto;
import com.ecommerce.authservice.model.User;
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
