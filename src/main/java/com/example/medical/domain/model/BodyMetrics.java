package com.example.medical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BodyMetrics {
private Long userId;
private String bloodPressure;

private String notes;
private Double sugarLevel;
private Double temperature;
private Double weight;

}
