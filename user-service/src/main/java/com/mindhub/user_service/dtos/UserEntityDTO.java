package com.mindhub.user_service.dtos;

public class UserEntityDTO {
    private final Long id;
    private final String username;
    private final String email;

    public UserEntityDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
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
