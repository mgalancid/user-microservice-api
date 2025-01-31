package com.mindhub.user_service.controllers;

import com.mindhub.user_service.config.JwtUtils;
import com.mindhub.user_service.dtos.auth.LoginUser;
import com.mindhub.user_service.dtos.auth.RegisterUser;
import com.mindhub.user_service.exceptions.UsernameNotFoundException;
import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
import com.mindhub.user_service.services.impl.UserEntityServiceImpl;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserEntityServiceImpl userService;

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public static final String
            USER_REGISTER_KEY = "registerUser.key";

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginUser loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found")
                );
        String role = user.getRole() != null ? user.getRole().toString() : RoleType.USER.toString();
        String jwt = jwtUtils.createToken(authentication.getName(), role);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser registerUser) {
        userService.registerUser(registerUser);
        amqpTemplate.convertAndSend("exchange", USER_REGISTER_KEY, registerUser);
        return ResponseEntity.ok("User registered successfully");
    }
}
