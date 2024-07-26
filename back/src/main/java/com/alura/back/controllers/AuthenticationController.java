package com.alura.back.controllers;

import com.alura.back.Dtos.requestDto.AuthCreateUserRequest;
import com.alura.back.Dtos.requestDto.AuthLoginRequest;
import com.alura.back.Dtos.responseDto.AuthResponse;
import com.alura.back.services.implementService.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest loginUserRequest) {
        log.info(loginUserRequest.userEmail());
        return new ResponseEntity<>(this.userDetailsService.loginUser(loginUserRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest createUserRequest) {
        return new ResponseEntity<>(this.userDetailsService.createUser(createUserRequest), HttpStatus.CREATED);
    }
}
