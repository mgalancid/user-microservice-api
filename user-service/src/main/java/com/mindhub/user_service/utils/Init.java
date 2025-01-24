package com.mindhub.user_service.utils;

import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity("john.doe@example.com", "John Doe", RoleType.USER);
        userRepository.save(user);
        UserEntity user2 = new UserEntity("jane.doe@example.com", "Jane Doe", RoleType.ADMIN);
        userRepository.save(user2);
    }
}

