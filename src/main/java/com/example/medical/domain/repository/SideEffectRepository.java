package com.example.medical.domain.repository;

import com.example.medical.domain.model.SideEffect;

import java.util.List;
import java.util.Optional;

public interface SideEffectRepository {
    SideEffect save(SideEffect sideEffect);
    Optional<SideEffect> findById(Long id);
    List<SideEffect> findByUserId(Long userId);
    List<SideEffect> findByMedicineId(Long medicineId);
    void deleteById(Long id);
}
