package com.example.decagon_Life_Project.repository;

import com.example.decagon_Life_Project.entities.UserEntity;
import com.example.decagon_Life_Project.models.UserEntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntitiesByEmail(String email);
    UserEntity save(UserEntity userEntity);
    UserEntity getUserEntitiesByToken(String token);
    Optional<UserEntity> findByEmail(String email);
}