package com.mindhub.user_service.utils;

import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity("John Doe",
                                         "john.doe@example.com",
                                         passwordEncoder.encode("12345678"),
                                         RoleType.USER);
        userRepository.save(user);
        UserEntity user2 = new UserEntity("Jane Doe",
                                          "jane.doe@example.com",
                                          passwordEncoder.encode("admin123"),
                                          RoleType.ADMIN);
        userRepository.save(user2);
    }
}

