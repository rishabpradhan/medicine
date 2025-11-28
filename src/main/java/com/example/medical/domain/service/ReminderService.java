package com.example.medical.domain.service;

import com.example.medical.domain.model.Reminder;
import com.example.medical.domain.repository.ReminderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReminderService {
    private ReminderRepository reminderRepository;
    public Reminder addReminder(Long medicineId, Instant remindAt, boolean active) {
        Reminder reminder = Reminder.builder()
                .id(null)
                .medicineId(medicineId)
                .remindAt(remindAt)
                .active(active)
                .build();
        return reminderRepository.save(reminder);
    }

    public Optional<Reminder> getReminder(Long id) {
        return reminderRepository.findById(id);
    }

    public List<Reminder> getRemindersByMedicine(Long medicineId) {
        return reminderRepository.findByMedicineId(medicineId);
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }
}
