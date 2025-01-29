package com.mindhub.user_service.controllers;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.services.impl.UserEntityServiceImpl;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserEntityController {

    @Autowired
    private UserEntityServiceImpl userService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public static final String
            USER_REGISTER_KEY = "registerUser.key";

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUsers() {
        List<UserEntityDTO> users = userService.getAllUsersDTO();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/email")
    public ResponseEntity<UserEntityDTO> getUserDTOByEmail(@RequestParam(name = "email") String email) {
        UserEntityDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntityDTO> createNewUser(@RequestBody NewUserEntityDTO newUserDTO) {
        UserEntityDTO createdUser = userService.createNewUser(newUserDTO);
        amqpTemplate.convertAndSend("exchange", USER_REGISTER_KEY, newUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleType>> getAllRoles() {
        List<RoleType> roles = userService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}

