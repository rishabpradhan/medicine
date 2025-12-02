package com.example.medical.domain.service;

import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.BodyMetricsRepository;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
@Service
@RequiredArgsConstructor
public class BodyMetricsService {
    private final BodyMetricsRepository bodyMetricsRepository;
    private final UserRepository userRepository;

    public BodyMetrics addMetrics(Users user,String userEmail, String bloodPressure, Double sugarLevel,
                                  Double temperature, Double weight, String notes) {



        BodyMetrics metrics = BodyMetrics.builder()
                .bloodPressure(bloodPressure)
                .sugarLevel(sugarLevel)
                .temperature(temperature)
                .weight(weight)
                .notes(notes)
                .build();

        // pass userId as second argument
        return bodyMetricsRepository.save(metrics, user.getId());
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
