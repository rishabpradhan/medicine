package com.example.medical.application.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDTO {
    private Long userId;
    private String name;
    private String email;
    private String role;
    private List<MedicineDTO> medicines;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MedicineDTO {
        private Long medicineId;
        private String name;
        private String dosage;
        private String IntakeTime;
    }
}
