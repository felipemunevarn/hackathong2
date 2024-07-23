package com.alura.back.Dtos.requestDto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthCreateRoleRequest(@Size(max = 3, message = "User can not have more than 3 roles") List<String> roleListName) {
}
