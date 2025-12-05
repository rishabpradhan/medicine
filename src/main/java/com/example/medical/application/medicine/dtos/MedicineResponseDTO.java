package com.example.medical.application.medicine.dtos;

import com.example.medical.domain.model.Medicine;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;

@Data
public class MedicineResponseDTO {
    private Long id;
    private String name;
    private String dosage;
    private LocalTime intakeTime;
    private String notes;
    private Boolean active;
    private Long userId;
    private Instant createdAt;

    // Constructor
    public MedicineResponseDTO(Medicine medicine){
        this.id = medicine.getId();
        this.name = medicine.getName();
        this.dosage = medicine.getDosage();
        this.intakeTime = medicine.getIntakeTime();
        this.notes = medicine.getNotes();
        this.active = medicine.getActive();
        this.userId = medicine.getUserId();
        this.createdAt = medicine.getCreatedAt();
    }
}
