package com.mindhub.user_service.services;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;
import com.mindhub.user_service.models.RoleType;

import java.util.List;

public interface UserEntityService {
    List<UserEntityDTO> getAllUsersDTO();
    UserEntityDTO getUserByEmail(String email);
    UserEntityDTO createNewUser(NewUserEntityDTO newUserDTO);
    List<RoleType> getAllRoles();
}
