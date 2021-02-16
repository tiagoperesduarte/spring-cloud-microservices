package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.controller.dto.AuthDto;
import com.ecommerce.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> createToken(@Valid @RequestBody AuthDto authDto) {
        log.debug("Request to create new token with data (data={})", authDto);

        String token = authService.authenticate(authDto.getUsername(), authDto.getPassword());
        return new ResponseEntity<>(Collections.singletonMap("accessToken", token), HttpStatus.OK);
    }
}
