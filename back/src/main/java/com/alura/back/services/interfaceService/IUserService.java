package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.responseDto.UserResponseDto;

import java.util.List;

public interface IUserService {

    public List<UserResponseDto> getUsers();
}
