package com.example.medical.application.bodymetrics.dtos;

import com.example.medical.domain.model.BodyMetrics;
import lombok.Data;

import java.time.Instant;
@Data
public class BodyMetricsResponseDTO {
    private String bloodPressure;
    private Double sugarLevel;
    private Double temperature;
    private Double weight;
    private String notes;



    public BodyMetricsResponseDTO(BodyMetrics metrics) {

        this.bloodPressure = metrics.getBloodPressure();
        this.sugarLevel = metrics.getSugarLevel();
        this.temperature = metrics.getTemperature();
        this.weight = metrics.getWeight();
        this.notes = metrics.getNotes();

    }
}
