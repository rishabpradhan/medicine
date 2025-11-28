package com.example.medical.infrastructure.mapper;

import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.infrastructure.entity.BodyMetricsEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BodyMetricsMapper {
    public BodyMetrics toDomain(BodyMetricsEntity entity) {
        if (entity == null) return null;

        return BodyMetrics.builder()
                .id(entity.getId())
                .bloodPressure(entity.getBloodPressure())
                .date(entity.getDate())
                .notes(entity.getNotes())
                .sugarLevel(entity.getSugarLevel())
                .temperature(entity.getTemperature())
                .weight(entity.getWeight())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .build();
    }

    public BodyMetricsEntity toEntity(BodyMetrics domain) {
        if (domain == null) return null;

        BodyMetricsEntity entity = BodyMetricsEntity.builder()
                .id(domain.getId())
                .bloodPressure(domain.getBloodPressure())
                .date(domain.getDate())
                .notes(domain.getNotes())
                .sugarLevel(domain.getSugarLevel())
                .temperature(domain.getTemperature())
                .weight(domain.getWeight())
                .build();

        return entity;
    }
}
