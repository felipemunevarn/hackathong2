package com.alura.back.mappers;

import com.alura.back.Dtos.requestDto.UserRequestDto;
import com.alura.back.Dtos.responseDto.UserResponseDto;
import com.alura.back.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto(
                user.getUser_id(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirths(),
                user.getGender(),
                user.getEmail(),
                user.getPassword(),
                user.getDevType(),
                user.getRole()
        );
        return dto;
    }

    public User toUser(UserRequestDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDateOfBirths(dto.getDateOfBirths());
        user.setGender(dto.getGender());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setDevType(dto.getDevType());
        user.setRole(dto.getRole());
        return user;
    }

    public List<UserResponseDto> toUserResponseDtos(List<User> users) {
        return users.stream().map(this::toUserResponseDto).collect(Collectors.toList());
    }
}
