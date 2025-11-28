package com.example.medical.infrastructure.repository;

import com.example.medical.infrastructure.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicineJpaRepository extends JpaRepository<MedicineEntity,Long> {
    List<MedicineEntity> findByUserId(Long userId);
}
