package com.example.medical.application.reminder.dtos;

import com.example.medical.domain.model.Reminder;
import lombok.Data;

import java.time.Instant;
@Data
public class ReminderResponseDTO {
    private Long id;
    private Long medicineId;
    private Instant remindAt;
    private Boolean active;

    public ReminderResponseDTO(Reminder reminder) {
        this.id = reminder.getId();
        this.medicineId = reminder.getMedicineId();
        this.remindAt = reminder.getRemindAt();
        this.active = reminder.isActive();
    }
}
