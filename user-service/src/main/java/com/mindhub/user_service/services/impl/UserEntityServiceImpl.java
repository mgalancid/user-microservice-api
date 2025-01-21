package com.mindhub.user_service.services.impl;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.repositories.UserEntityRepository;
import com.mindhub.user_service.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userRepository;

    @Override
    public List<UserEntityDTO> getAllUsersDTO() {
        return List.of();
    }

    @Override
    public NewUserEntityDTO createNewUser(NewUserEntityDTO newUserDTO) {
        return null;
    }

    @Override
    public List<UserEntityDTO> getAllRoles() {
        return List.of();
    }
}
