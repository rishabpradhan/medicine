package com.example.medical.infrastructure.repository;

import com.example.medical.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  java.util.*;


@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

}
