package com.example.medical.infrastructure.adapter;

import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.MedicineRepository;
import com.example.medical.infrastructure.entity.BodyMetricsEntity;
import com.example.medical.infrastructure.entity.MedicineEntity;
import com.example.medical.infrastructure.entity.UserEntity;
import com.example.medical.infrastructure.mapper.BodyMetricsMapper;
import com.example.medical.infrastructure.mapper.MedicineMapper;
import com.example.medical.infrastructure.mapper.UserMapper;
import com.example.medical.infrastructure.repository.MedicineJpaRepository;
import com.example.medical.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataMedicineRepository implements MedicineRepository {
private final UserJpaRepository userJpaRepository;
    private final MedicineJpaRepository jpaRepository;

    @Override
    public Medicine save(Medicine medicine, Long userId) {
        // Fetch UserEntity
        UserEntity user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Map domain to entity
        MedicineEntity entity = MedicineMapper.toEntity(medicine);
        entity.setUser(user);  //

        entity = jpaRepository.save(entity);
        return MedicineMapper.toDomain(entity);
    }


    @Override
    public Optional<Medicine> findById(Long id) {
        return jpaRepository.findById(id)
                .map(MedicineMapper::toDomain);
    }

    @Override
    public List<Medicine> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(entity -> MedicineMapper.toDomain(entity))  // Explicit lambda
                .toList();
    }

    @Override
//    public List<Medicine> findAll() {
//        return jpaRepository.findAll()
//                .stream()
//                .map(entity -> MedicineMapper.toDomain(entity))
//                .toList();
//    }
    public List<Medicine> findAll() {
        return jpaRepository.findAll().stream()
                .map(MedicineMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
