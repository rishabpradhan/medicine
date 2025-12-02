package com.example.medical.infrastructure.mapper;

import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.infrastructure.entity.BodyMetricsEntity;
import com.example.medical.infrastructure.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BodyMetricsMapper {

    // Entity → Domain
    public BodyMetrics toDomain(BodyMetricsEntity entity) {
        if (entity == null) return null;

        return BodyMetrics.builder()
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .bloodPressure(entity.getBloodPressure())
                .sugarLevel(entity.getSugarLevel())
                .temperature(entity.getTemperature())
                .weight(entity.getWeight())
                .notes(entity.getNotes())
                .build();
    }

    // Domain → Entity
    public BodyMetricsEntity toEntity(BodyMetrics domain) {
        if (domain == null) return null;

        BodyMetricsEntity entity = BodyMetricsEntity.builder()
                .bloodPressure(domain.getBloodPressure())
                .sugarLevel(domain.getSugarLevel())
                .temperature(domain.getTemperature())
                .weight(domain.getWeight())
                .notes(domain.getNotes())
                .build();

        return entity;
    }
}
