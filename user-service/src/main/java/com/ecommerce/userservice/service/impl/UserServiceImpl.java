package com.ecommerce.userservice.service.impl;

import com.ecommerce.userservice.exception.UserAlreadyExistsException;
import com.ecommerce.userservice.exception.UserNotFoundException;
import com.ecommerce.userservice.integration.queue.UserProducer;
import com.ecommerce.userservice.integration.queue.mapper.UserMessageMapper;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProducer userProducer;
    private final UserMessageMapper userMessageMapper;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserProducer userProducer,
            UserMessageMapper userMessageMapper
    ) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
        this.userMessageMapper = userMessageMapper;
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("There already exists a user with email " + user.getEmail());
        }

        user.setId(null);
        user.setCreatedOn(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        userProducer.sendUserSaveMessage(userMessageMapper.toDto(savedUser));

        return savedUser;
    }

    @Override
    public User updateUser(String id, User user) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }

        if (userRepository.existsByIdNotAndEmail(id, user.getEmail())) {
            throw new UserAlreadyExistsException("There already exists a user with email " + user.getEmail());
        }

        user.setId(id);
        User savedUser = userRepository.save(user);
        userProducer.sendUserSaveMessage(userMessageMapper.toDto(savedUser));

        return savedUser;
    }

    @Override
    public void deleteUserById(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }

        userRepository.deleteById(id);
    }
}