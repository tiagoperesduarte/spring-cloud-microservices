package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.controller.dto.UserDto;
import com.ecommerce.userservice.controller.mapper.UserMapper;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public Page<UserDto> getUsers(Pageable pageable) {
        log.debug("Request to find all users by query (query={})", pageable);

        return userService.getUsers(pageable)
                .map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        log.debug("Request to find user by id (id={})", id);

        return userMapper.toDto(userService.getUserById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.debug("Request to create new user with data (data={})", userDto);

        User user = userService.createUser(userMapper.toModel(userDto));
        return userMapper.toDto(user);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable String id, @Valid @RequestBody UserDto userDto) {
        log.debug("Request to update user by id (id={}) with data (data={})", id, userDto);

        User user = userService.updateUser(id, userMapper.toModel(userDto));
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        log.debug("Request to delete user by id (id={})", id);

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
