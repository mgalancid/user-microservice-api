package com.mindhub.user_service.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUser(@NotNull String username,
                           @NotNull @Email String email,
                           @NotNull @Size(min=8) String password) {
}
