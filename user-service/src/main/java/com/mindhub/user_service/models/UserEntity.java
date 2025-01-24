package com.mindhub.user_service.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Username cannot be null")
    private String username;

    @Column(unique = true)
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Role cannot be null")
    private RoleType role;

    //private Long orderId;

    public UserEntity() {

    }

    public UserEntity(String email, String username, RoleType role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
