package com.ecommerce.userservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;
    private String email;

    @Transient
    private String password;

    private String telephone;
    private List<UserAddress> addresses;
    private LocalDateTime createdOn;
}
