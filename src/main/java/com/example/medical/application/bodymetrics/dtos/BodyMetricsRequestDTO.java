package com.example.medical.application.bodymetrics.dtos;


import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class BodyMetricsRequestDTO {

    private String bloodPressure;
    private Double sugarLevel;
    private Double temperature;
    private Double weight;

    @Size(max = 255, message = "Notes max length 255")
    private String notes;


}
