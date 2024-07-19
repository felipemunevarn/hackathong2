package com.alura.back.services.implementService;

import com.alura.back.Dtos.responseDto.UserResponseDto;
import com.alura.back.entities.User;
import com.alura.back.mappers.UserMapper;
import com.alura.back.repositories.UserRepository;
import com.alura.back.services.interfaceService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserResponseDtos(users);
    }
}
