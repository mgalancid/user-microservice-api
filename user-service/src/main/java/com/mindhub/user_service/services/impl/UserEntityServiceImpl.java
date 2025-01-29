package com.mindhub.user_service.services.impl;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.exceptions.InvalidUserException;
import com.mindhub.user_service.exceptions.NoUsersFoundException;
import com.mindhub.user_service.exceptions.UserAlreadyExistsException;
import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
import com.mindhub.user_service.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userRepository;

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
    public UserEntityDTO getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(
                () ->  new InvalidUserException("User not found with email: " + email)
        );
        return new UserEntityDTO(user.getId(),user.getUsername(), user.getEmail());
    }

    @Override
    @Transactional(rollbackFor = InvalidUserException.class)
    public UserEntityDTO createNewUser(NewUserEntityDTO newUserDTO) throws InvalidUserException {
        if (userRepository.existsByEmail(newUserDTO.getEmail())) {
            throw new UserAlreadyExistsException("A user with the email '" + newUserDTO.getEmail() + "' already exists.");
        }

        if (newUserDTO.getUsername() == null || newUserDTO.getUsername().isEmpty()) {
            throw new InvalidUserException("Username cannot be null or empty.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(newUserDTO.getUsername());
        user.setEmail(newUserDTO.getEmail());
        user.setRole(newUserDTO.getRole()!= null ? newUserDTO.getRole() : RoleType.USER);

        UserEntity savedUser = userRepository.save(user);
        return new UserEntityDTO(savedUser);
    }

    @Override
    public List<RoleType> getAllRoles() {
        return List.of(RoleType.values());
    }

    /*
    public void validateEntries(UserEntityDTO userDTO) {
        if (userDTO.getEmail().isBlank()) {

        }
    }

     */
}

