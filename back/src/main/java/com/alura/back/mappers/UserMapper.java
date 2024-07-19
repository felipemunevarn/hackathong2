package com.alura.back.mappers;

import com.alura.back.Dtos.responseDto.UserResponseDto;
import com.alura.back.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto(
                user.getUser_Id(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirths(),
                user.getGender(),
                user.getEmail(),
                user.getDevType(),
                user.getRole()
        );

        return dto;
    }

    public List<UserResponseDto> toUserResponseDtos(List<User> users) {
        return users.stream().map(this::toUserResponseDto).collect(Collectors.toList());
    }
}
