package com.example.medical.application.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminBodyMetricDTO {


    private Long id;
    private String bloodPressure;
    private String sugarLevel;
    private String temperature;
    private String weight;
    private String notes;
}
