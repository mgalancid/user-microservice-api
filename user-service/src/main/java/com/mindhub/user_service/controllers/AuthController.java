package com.mindhub.user_service.controllers;

import com.mindhub.user_service.config.JwtUtils;
import com.mindhub.user_service.dtos.LoginUser;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginUser loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = userService.findByEmail(authentication.getName()).orElseThrow(null);
        String jwt = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser registerUser) {
        userService.registerUser(registerUser);
        return ResponseEntity.ok("User registered successfully");
    }
}
