package com.example.medical.application.controller;

import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {
private final MedicineRepository medicineRepository;
    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        Medicine saved = medicineRepository.save(medicine);
        return ResponseEntity.ok(saved);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        return medicineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Medicine>> getMedicinesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineRepository.findByUserId(userId));
    }
}
