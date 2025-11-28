package com.example.medical.infrastructure.adapter;

import com.example.medical.domain.model.SideEffect;

import com.example.medical.domain.repository.SideEffectRepository;
import com.example.medical.infrastructure.mapper.SideEffectMapper;

import com.example.medical.infrastructure.repository.SideEffectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
@RequiredArgsConstructor
@Component
public class SpringDataSideEffectJpaRepository implements SideEffectRepository {

    private final SideEffectJpaRepository jpaRepository;

    public SideEffect save(SideEffect sideEffect){
        var entity=SideEffectMapper.toEntity(sideEffect);
        entity=jpaRepository.save(entity);
        return SideEffectMapper.toDomain(entity);
    }

    public Optional<SideEffect> findById(Long id) {
        return jpaRepository.findById(id)
                .map(SideEffectMapper::toDomain);
    }
    @Override
    public List<SideEffect> findByUserId(Long userId) {
        // jpaRepository.findByUserId() now returns List<SideEffectEntity>
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(SideEffectMapper::toDomain) // This is now correct: maps Entity -> Domain
                .toList();
    }

    @Override
    public List<SideEffect> findByMedicineId(Long medicineId) {

        return jpaRepository.findByMedicineId(medicineId)
                .stream()
               //.map(SideEffectMapper::toDomain) // This is now correct: maps Entity -> Domain
                .toList();
    }
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }




}
