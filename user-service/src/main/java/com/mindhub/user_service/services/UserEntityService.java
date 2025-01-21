package com.mindhub.user_service.services;

import com.mindhub.user_service.dtos.NewUserEntityDTO;
import com.mindhub.user_service.dtos.UserEntityDTO;

import java.util.List;

public interface UserEntityService {
    List<UserEntityDTO> getAllUsersDTO();
    NewUserEntityDTO createNewUser(NewUserEntityDTO newUserDTO);
    List<UserEntityDTO> getAllRoles();
}
