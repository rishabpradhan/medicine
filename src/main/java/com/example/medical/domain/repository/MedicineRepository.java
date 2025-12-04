package com.example.medical.domain.repository;

import com.example.medical.domain.model.Medicine;
import org.apache.catalina.User;

import java.util.*;

public interface MedicineRepository {
    Medicine save(Medicine medicine,Long userId);

    Optional<Medicine> findById(Long id);

    List<Medicine> findByUserId(Long id);

    List<Medicine> findAll();

    void deleteById(Long id);
}
