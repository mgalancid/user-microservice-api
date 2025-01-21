package com.mindhub.user_service.services.impl;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.models.RoleType;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserEntityRepository;
import com.mindhub.user_service.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public List<UserEntityDTO> getAllUsersDTO() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserEntityDTO(user.getId(),
                                                        user.getUsername(),
                                                        user.getEmail()))
                .collect(Collectors.toList()
                );
    }

    @Override
    public NewUserEntityDTO createNewUser(NewUserEntityDTO newUserDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(newUserDTO.getUsername());
        user.setEmail(newUserDTO.getEmail());
        user.setRole(newUserDTO.getRole());
        UserEntity savedUser = userRepository.save(user);

        return new NewUserEntityDTO(savedUser.getUsername(),
                                    savedUser.getEmail(),
                                    savedUser.getRole()
        );
    }

    @Override
    public List<RoleType> getAllRoles() {
        return List.of(RoleType.values());
    }
}
