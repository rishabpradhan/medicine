package com.example.medical.infrastructure.mapper;

import com.example.medical.domain.model.Medicine;
import com.example.medical.infrastructure.entity.MedicineEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MedicineMapper {

    public Medicine toDomain(MedicineEntity entity){
        if(entity == null) return null;

        return Medicine.builder()
                .id(entity.getId())
                .name(entity.getName())
                .dosage(entity.getDosage())
                .intakeTime(entity.getIntakeTime())
                .notes(entity.getNotes())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .reminders(entity.getReminders() != null ?
                        entity.getReminders().stream()
                                .map(r -> ReminderMapper.toDomain(r))
                                .collect(Collectors.toList())
                        : List.of())
                .sideEffects(entity.getSideEffects() != null ?
                        entity.getSideEffects().stream()
                                .map(se -> SideEffectMapper.toDomain(se))
                                .collect(Collectors.toList())
                        : List.of())
                .build();
    }

    public MedicineEntity toEntity(Medicine domain){
        if(domain == null) return null;

        MedicineEntity entity = MedicineEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .dosage(domain.getDosage())
                .intakeTime(domain.getIntakeTime())
                .notes(domain.getNotes())
                .active(domain.isActive())
                .createdAt(domain.getCreatedAt())
                .build();

        return entity;
    }
}
