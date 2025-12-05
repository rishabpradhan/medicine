package com.example.medical.domain.service;

import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.SideEffect;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.SideEffectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
@Service
@RequiredArgsConstructor
public class SideEffectService {
    private final SideEffectRepository sideEffectRepository;

    public SideEffect addSideEffect(Users user, Long userId, Medicine medicine, String effect, Instant date) {
       ;

        SideEffect sideEffect = SideEffect.builder()

                .userId(user.getId())
                .medicineId(medicine.getId())
                .effect(effect)
                .date(date)
                .build();
        return sideEffectRepository.save(sideEffect,userId,medicine.getId());
    }

    public Optional<SideEffect> getSideEffect(Long id) {
        return sideEffectRepository.findById(id);
    }

    public List<SideEffect> getByUser(Long userId) {
        return sideEffectRepository.findByUserId(userId);
    }
    public List<SideEffect> getByMedicine(Long userId) {
        return sideEffectRepository.findByMedicineId(userId);
    }

    public void deleteSideEffect(Long id) {
        sideEffectRepository.deleteById(id);
    }
}
