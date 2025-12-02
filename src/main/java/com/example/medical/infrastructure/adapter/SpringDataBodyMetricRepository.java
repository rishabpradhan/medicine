package com.example.medical.infrastructure.adapter;

import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.domain.repository.BodyMetricsRepository;
import com.example.medical.infrastructure.entity.BodyMetricsEntity;
import com.example.medical.infrastructure.entity.UserEntity;
import com.example.medical.infrastructure.mapper.BodyMetricsMapper;
import com.example.medical.infrastructure.repository.BodyMetricsJpaRepository;
import com.example.medical.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
@RequiredArgsConstructor
public class SpringDataBodyMetricRepository implements BodyMetricsRepository {
    private final BodyMetricsJpaRepository jpaRepository;
    private final UserJpaRepository userJpaRepository;

    public BodyMetrics save(BodyMetrics metrics, Long userId) {
        UserEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BodyMetricsEntity entity = BodyMetricsMapper.toEntity(metrics);
        entity.setUser(user);
        entity.setDate(java.time.Instant.now());

        entity = jpaRepository.save(entity);
        return BodyMetricsMapper.toDomain(entity);

    }
        public Optional<BodyMetrics> findById(Long id) {
        return jpaRepository.findById(id).map(BodyMetricsMapper::toDomain);
    }

    public List<BodyMetrics> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(BodyMetricsMapper::toDomain)
                .collect(Collectors.toList());
    }
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

}
