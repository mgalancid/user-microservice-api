package com.mindhub.user_service.services;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.dtos.auth.RegisterUser;
import com.mindhub.user_service.models.RoleType;

import java.util.List;

public interface UserEntityService {
    List<UserEntityDTO> getAllUsersDTO();
    UserEntityDTO getUserDTOById(Long id);
    UserEntityDTO getUserByEmail(String email);
    List<RoleType> getAllRoles();
    void registerUser(RegisterUser registerUser);
}
