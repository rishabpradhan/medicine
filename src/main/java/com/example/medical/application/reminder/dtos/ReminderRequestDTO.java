package com.example.medical.application.reminder.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
@Data
public class ReminderRequestDTO {
    @NotNull(message = "Medicine ID is required")
    private Long medicineId;

    @NotNull(message = "RemindAt is required")
    private Instant remindAt;

    @NotNull(message = "Active status is required")
    private Boolean active;
}
