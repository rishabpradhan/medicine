package com.example.medical.infrastructure.mapper;

import com.example.medical.domain.model.Users;
import com.example.medical.infrastructure.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public Users toDomain(UserEntity entity) {
        if (entity == null)
            return null;
        return Users.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole() != null ? entity.getRole() : "USER")
                .age(entity.getAge())
                .gender(entity.getGender())
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt() : Instant.now())
                .medicines(entity.getMedicines().stream()
                        .map(MedicineMapper::toDomain)
                        .collect(Collectors.toList()))
                .sideEffects(entity.getSideEffects().stream()
                        .map(SideEffectMapper::toDomain)
                        .collect(Collectors.toList()))
                .bodyMetrics(entity.getBodyMetrics().stream()
                        .map(BodyMetricsMapper::toDomain)
                        .collect(Collectors.toList()))
                .build();
    }

    public UserEntity toEntity(Users domain) {
        if (domain == null) return null;

        UserEntity entity = UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .role(domain.getRole())
                .age(domain.getAge())
                .gender(domain.getGender())
                .createdAt(domain.getCreatedAt())
                .build();

        // set medicines
        if (domain.getMedicines() != null) {
            domain.getMedicines().forEach(med -> {
                entity.getMedicines().add(MedicineMapper.toEntity(med));
            });
        }
        return entity;

    }
}