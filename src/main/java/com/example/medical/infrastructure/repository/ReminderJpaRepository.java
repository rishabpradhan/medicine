package com.example.medical.infrastructure.repository;

import com.example.medical.infrastructure.entity.ReminderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderJpaRepository extends JpaRepository<ReminderEntity,Long> {
    List<ReminderEntity> findByMedicineId(long medicineId);
}
