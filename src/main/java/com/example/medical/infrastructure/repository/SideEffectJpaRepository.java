package com.example.medical.infrastructure.repository;

import com.example.medical.domain.model.SideEffect;
import com.example.medical.infrastructure.entity.SideEffectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SideEffectJpaRepository extends JpaRepository<SideEffectEntity,Long> {
    List<SideEffectEntity> findByUserId(Long userId);
    List<SideEffect> findByMedicineId(Long medicineId);
}
