package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.responseDto.UserResponseDto;
import com.alura.back.Dtos.requestDto.UserRequestDto;

import java.util.List;

public interface IUserService {

    public List<UserResponseDto> getUsers();
    public UserResponseDto getUserById(Long id);
    public UserResponseDto createUser(UserRequestDto userRequestDto);
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    public void deleteUser(Long id);
}
