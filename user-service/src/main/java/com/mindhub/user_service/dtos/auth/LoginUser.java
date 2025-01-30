package com.mindhub.user_service.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginUser(@NotNull @Email String email,
                        @NotNull @Size(min = 8) String password) {
}
