package com.mindhub.user_service.dtos;

import com.mindhub.user_service.models.UserEntity;

public class UserEntityDTO {
    private final Long id;
    private final String username;
    private final String email;

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
