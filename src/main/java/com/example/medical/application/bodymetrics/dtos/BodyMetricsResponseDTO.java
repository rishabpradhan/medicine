package com.example.medical.application.bodymetrics.dtos;

import com.example.medical.domain.model.BodyMetrics;
import lombok.Data;

import java.time.Instant;
@Data
public class BodyMetricsResponseDTO {
    private Long id;
    private Long userId;
    private String bloodPressure;
    private Double sugarLevel;
    private Double temperature;
    private Double weight;
    private String notes;
    private Instant date;

    public BodyMetricsResponseDTO(BodyMetrics metrics) {
        this.id = metrics.getId();
        this.userId = metrics.getUserId();
        this.bloodPressure = metrics.getBloodPressure();
        this.sugarLevel = metrics.getSugarLevel();
        this.temperature = metrics.getTemperature();
        this.weight = metrics.getWeight();
        this.notes = metrics.getNotes();
        this.date = metrics.getDate();
    }
}
