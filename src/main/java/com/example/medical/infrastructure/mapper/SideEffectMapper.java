package com.example.medical.infrastructure.mapper;

import com.example.medical.domain.model.SideEffect;
import com.example.medical.infrastructure.entity.SideEffectEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SideEffectMapper {

    public SideEffect toDomain(SideEffectEntity entity) {
        if (entity == null) return null;

        return SideEffect.builder()
                .id(entity.getId())
                .effect(entity.getEffect())
                .date(entity.getDate())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .medicineId(entity.getMedicine() != null ? entity.getMedicine().getId() : null)
                .build();
    }

    public SideEffectEntity toEntity(SideEffect domain) {
        if (domain == null) return null;

        return SideEffectEntity.builder()
                .id(domain.getId())
                .effect(domain.getEffect())
                .date(domain.getDate())
                //.userId(domain.getUserId())
                 //.medicineId(domain.getMedicineId())
                .build();
    }
}
