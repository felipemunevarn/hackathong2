package com.alura.back.controllers;

import com.alura.back.Dtos.responseDto.UserResponseDto;
import com.alura.back.entities.User;
import com.alura.back.services.implementService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }
}
