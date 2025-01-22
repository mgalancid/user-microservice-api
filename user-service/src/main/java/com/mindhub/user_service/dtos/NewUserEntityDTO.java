package com.mindhub.user_service.dtos;

import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import org.apache.catalina.User;

public class NewUserEntityDTO {
    private final String username;
    private final String email;
    private final RoleType role;

    public NewUserEntityDTO(UserEntity user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
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
