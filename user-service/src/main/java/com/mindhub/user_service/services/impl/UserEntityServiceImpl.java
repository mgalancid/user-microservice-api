package com.mindhub.user_service.services.impl;

import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.dtos.auth.RegisterUser;
import com.mindhub.user_service.exceptions.InvalidUserException;
import com.mindhub.user_service.exceptions.NoUsersFoundException;
import com.mindhub.user_service.exceptions.UserAlreadyExistsException;
import com.mindhub.user_service.exceptions.UserNotFoundException;
import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
import com.mindhub.user_service.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntityDTO> getAllUsersDTO() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoUsersFoundException("No users found in the system.");
        }
        return users.stream()
                .map(user -> new UserEntityDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserEntityDTO getUserDTOById(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id: " + id)
        );
        return new UserEntityDTO(user);
    }

    @Override
    public UserEntityDTO getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () ->  new InvalidUserException("User not found with email: " + email)
        );
        return new UserEntityDTO(user.getId(),user.getUsername(), user.getEmail());
    }

    @Override
    public List<RoleType> getAllRoles() {
        return List.of(RoleType.values());
    }

    @Override
    @Transactional
    public void registerUser(RegisterUser registerUser) {
        if (userRepository.existsByEmail(registerUser.email())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setEmail(registerUser.email());
        user.setPassword(passwordEncoder.encode(registerUser.password()));
        user.setUsername(registerUser.username());
        user.setRole(RoleType.USER);

        userRepository.save(user);
    }


}

