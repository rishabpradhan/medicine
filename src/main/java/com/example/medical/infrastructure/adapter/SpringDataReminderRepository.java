package com.example.medical.infrastructure.adapter;

import com.example.medical.domain.model.Reminder;
import com.example.medical.domain.repository.ReminderRepository;
import com.example.medical.infrastructure.mapper.ReminderMapper;
import com.example.medical.infrastructure.repository.ReminderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataReminderRepository implements ReminderRepository {
private final ReminderJpaRepository jpaRepository;
    public Reminder save(Reminder reminder) {
        var entity = ReminderMapper.toEntity(reminder);
        entity = jpaRepository.save(entity);
        return ReminderMapper.toDomain(entity);
    }
    public Optional<Reminder> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ReminderMapper::toDomain);
    }
    public List<Reminder> findByMedicineId(Long medicineId) {
        return jpaRepository.findByMedicineId(medicineId)
                .stream()
                .map(ReminderMapper::toDomain)
                .toList();
    }
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    public List<Reminder> findAll(){
        return jpaRepository.findAll()
                .stream()
                .map(ReminderMapper::toDomain)
                .toList();
    }
}
