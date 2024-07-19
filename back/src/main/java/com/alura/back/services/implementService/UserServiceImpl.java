package com.alura.back.services.implementService;

import com.alura.back.Dtos.requestDto.UserRequestDto;
import com.alura.back.Dtos.responseDto.UserResponseDto;
import com.alura.back.entities.User;
import com.alura.back.mappers.UserMapper;
import com.alura.back.repositories.UserRepository;
import com.alura.back.services.interfaceService.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toUserResponseDto).orElse(null);
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        user = userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userRequestDto.getFirstName());
            user.setLastName(userRequestDto.getLastName());
            user.setDateOfBirths(userRequestDto.getDateOfBirths());
            user.setGender(userRequestDto.getGender());
            user.setEmail(userRequestDto.getEmail());
            user.setDevType(userRequestDto.getDevType());
            user.setRole(userRequestDto.getRole());
            user = userRepository.save(user);
            return userMapper.toUserResponseDto(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
