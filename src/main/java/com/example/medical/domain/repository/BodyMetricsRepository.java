package com.example.medical.domain.repository;

import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.infrastructure.entity.UserEntity;

import java.util.*;
public interface BodyMetricsRepository {
    BodyMetrics save(BodyMetrics bodyMetrics, Long userId);
    Optional<BodyMetrics> findById(Long id);
    List<BodyMetrics> findByUserId(Long userId);
    void deleteById(Long id);
}
