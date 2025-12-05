package com.example.medical.domain.repository;

import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.SideEffect;

import java.util.List;
import java.util.Optional;

public interface SideEffectRepository {
    SideEffect save(SideEffect sideEffect, Long userId,Long medicineId);
    Optional<SideEffect> findById(Long id);
    List<SideEffect> findByUserId(Long userId);
    List<SideEffect> findByMedicineId(Long medicineId);
    void deleteById(Long id);

}
