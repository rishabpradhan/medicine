package com.example.medical.infrastructure.repository;

import com.example.medical.infrastructure.entity.BodyMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BodyMetricsJpaRepository extends JpaRepository<BodyMetricsEntity,Long> {
List<BodyMetricsEntity> findByUserId(Long userId);
}
