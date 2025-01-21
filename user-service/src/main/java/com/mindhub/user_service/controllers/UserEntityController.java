package com.mindhub.user_service.controllers;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.services.impl.UserEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserEntityController {

    @Autowired
    private UserEntityServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUsers() {
        List<UserEntityDTO> users = userService.getAllUsersDTO();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<NewUserEntityDTO> createNewUser(@RequestBody NewUserEntityDTO newUserDTO) {
        NewUserEntityDTO createdUser = userService.createNewUser(newUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleType>> getAllRoles() {
        List<RoleType> roles = userService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}

