package com.mindhub.user_service.dtos;

import com.mindhub.user_service.models.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserEntityDTO {
    @NotNull
    private final Long id;

    @NotNull
    @Size(min = 8, max = 50)
    private final String username;

    @NotNull
    @Email
    private final String email;

    public UserEntityDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UserEntityDTO(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
