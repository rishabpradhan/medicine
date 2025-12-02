package com.example.medical.domain.service;

import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public Medicine addMedicine(Users user,String name, String dosage, LocalTime intakeTime, String notes, boolean active
            ) {
        Medicine medicine = Medicine.builder()
                .userId(user.getId())
                .name(name)
                .dosage(dosage)
                .intakeTime(intakeTime)
                .notes(notes)
                .active(active)

                .createdAt(Instant.now())
                .build();
        return medicineRepository.save(medicine);

    }

    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public List<Medicine> getMedicineByUser(Long userId) {
        return medicineRepository.findByUserId(userId);
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
    public List<Medicine> getAllUsers(){
        return  medicineRepository.findAll();
    }
}
