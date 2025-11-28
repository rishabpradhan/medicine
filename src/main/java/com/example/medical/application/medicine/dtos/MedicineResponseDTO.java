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

    MedicineResponseDTO(Medicine medicine){
        id=this.getId();
        name=this.getName();
        dosage=this.getDosage();
        intakeTime=this.getIntakeTime();
        notes=this.getNotes();
        active=this.getActive();
        userId=this.getUserId();
        createdAt=this.getCreatedAt();




    }

}
