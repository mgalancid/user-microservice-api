package com.mindhub.user_service.dtos;

import com.mindhub.user_service.models.RoleType;

public class NewUserEntityDTO {
    private final String username;
    private final String email;
    private final RoleType role;

    public NewUserEntityDTO(String username, String email, RoleType role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public RoleType getRole() {
        return role;
    }
}
