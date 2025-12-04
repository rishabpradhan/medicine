package com.example.medical.application.sideeffect.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
@Data
public class SideEffectRequestDTO {
    @NotNull(message = "User ID is required")
    private Long userId;
@NotNull(message = "medicineId is required")
    private Long medicineId;

    @NotBlank(message = "Effect description is required")
    private String effect;

    @NotNull(message = "Date is required")
    private Instant date;
}
