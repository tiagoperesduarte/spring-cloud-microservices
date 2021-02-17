package com.ecommerce.authservice.service;

import com.ecommerce.authservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getUsers(Pageable pageable);

    User getUserById(String id);

    User createUser(User user);

    User updateUser(String id, User user);

    void deleteUserById(String id);
}
