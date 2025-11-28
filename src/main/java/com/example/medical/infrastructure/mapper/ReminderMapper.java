package com.example.medical.infrastructure.mapper;

import com.example.medical.domain.model.Reminder;
import com.example.medical.infrastructure.entity.ReminderEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReminderMapper {
    public Reminder toDomain(ReminderEntity entity) {
        if (entity == null) return null;

        return Reminder.builder()
                .id(entity.getId())
                .active(entity.isActive())
                .remindAt(entity.getRemindAt())
                .medicineId(entity.getMedicine() != null ? entity.getMedicine().getId() : null)
                .build();
    }

    public ReminderEntity toEntity(Reminder domain) {
        if (domain == null) return null;

        ReminderEntity entity = ReminderEntity.builder()
                .id(domain.getId())
                .active(domain.isActive())
                .remindAt(domain.getRemindAt())
                .build();

        return entity;
    }

}
