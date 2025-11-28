package com.example.medical.application.bodymetrics.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
@Data
public class BodyMetricsRequestDTO {
    @NotNull(message = "User ID is required")
    private Long userId;

    private String bloodPressure;
    private Double sugarLevel;
    private Double temperature;
    private Double weight;

    @Size(max = 255, message = "Notes max length 255")
    private String notes;

    @NotNull(message = "Date is required")
    private Instant date;
}
