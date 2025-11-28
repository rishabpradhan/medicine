package com.example.medical.domain.service;

import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.domain.repository.BodyMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
@Service
@RequiredArgsConstructor
public class BodyMetricsService {
    private final BodyMetricsRepository bodyMetricsRepository;

    public BodyMetrics addMetrics(Long userId, String bloodPressure, Double sugarLevel, Double temperature, Double weight, String notes, Instant date) {
        BodyMetrics metrics = BodyMetrics.builder()
                .id(null)
                .userId(userId)
                .bloodPressure(bloodPressure)
                .sugarLevel(sugarLevel)
                .temperature(temperature)
                .weight(weight)
                .notes(notes)
                .date(date)
                .build();
        return bodyMetricsRepository.save(metrics);
    }

    public Optional<BodyMetrics> getMetrics(Long id) {
        return bodyMetricsRepository.findById(id);
    }

    public List<BodyMetrics> getMetricsByUser(Long userId) {
        return bodyMetricsRepository.findByUserId(userId);
    }

    public void deleteMetrics(Long id) {
        bodyMetricsRepository.deleteById(id);
    }
}
