package com.example.medical.application.controller;
import com.example.medical.domain.model.Reminder;
import com.example.medical.domain.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderRepository reminderRepository;

    @PostMapping
    public ResponseEntity<Reminder> addReminder(@RequestBody  Reminder reminder){
        return ResponseEntity.ok(reminderRepository.save(reminder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getById(@PathVariable Long id){
        return reminderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<Reminder>> getByMedicine(@PathVariable Long medicineId) {
        return ResponseEntity.ok(reminderRepository.findByMedicineId(medicineId));
    }
}
