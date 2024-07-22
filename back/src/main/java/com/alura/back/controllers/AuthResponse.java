package com.alura.back.controllers;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userEmail", "message", "jwt", "status"})
public record AuthResponse(String userEmail,
                           String message,
                           String jwt,
                           boolean status) {
}
