package com.example.medical.application.medicine.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;

@Data
public class MedicineRequestDTO {
@NotBlank(message = "Medicine name is requried")
    private String name;

@NotBlank(message="Dosage is required")
    private String dosage;
@JsonFormat(pattern = "HH:mm")
private LocalTime intakeTime;

@Size(max=500,message = "Upto 500 word only")
private String notes;

@NotNull(message = "Active is required")
    private Boolean active;
//    @NotNull(message = "User ID is required")
//    private Long userId;
//


}
