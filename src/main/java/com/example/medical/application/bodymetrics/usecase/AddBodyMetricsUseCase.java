package com.example.medical.application.bodymetrics.usecase;

import com.example.medical.application.bodymetrics.dtos.BodyMetricsRequestDTO;
import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.domain.service.BodyMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class AddBodyMetricsUseCase {
    private final BodyMetricsService bodyMetricsService;

    public BodyMetrics execute(BodyMetricsRequestDTO dto) {
        return bodyMetricsService.addMetrics(
                dto.getUserId(),
                dto.getBloodPressure(),
                dto.getSugarLevel(),
                dto.getTemperature(),
                dto.getWeight(),
                dto.getNotes(),
                dto.getDate()
        );
    }
}
