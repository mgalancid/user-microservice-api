package com.mindhub.user_service.repositories;

import com.mindhub.user_service.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
