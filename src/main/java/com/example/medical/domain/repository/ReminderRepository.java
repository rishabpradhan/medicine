package com.example.medical.domain.repository;
import com.example.medical.domain.model.Reminder;

import java.util.*;
public interface ReminderRepository {
    Reminder save(Reminder reminder);
    Optional<Reminder> findById(Long id);
    List<Reminder> findByMedicineId(Long medicineId);
    List<Reminder> findAll();
    void deleteById(Long id);
}
