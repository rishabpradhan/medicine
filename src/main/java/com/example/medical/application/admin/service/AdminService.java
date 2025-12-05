package com.example.medical.application.admin.service;

import com.example.medical.application.admin.dtos.AdminBodyMetricDTO;
import com.example.medical.application.admin.dtos.AdminLoginRequestDTO;
import com.example.medical.application.admin.dtos.AdminUserDTO;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.BodyMetricsRepository;
import com.example.medical.domain.repository.MedicineRepository;
import com.example.medical.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;
    private final PasswordEncoder passwordEncoder;
    private final BodyMetricsRepository bodyMetricsRepository;

    // Generic exception class
    public static class AdminServiceException extends RuntimeException {
        public AdminServiceException(String message) {
            super(message);
        }
    }

    public Users login(AdminLoginRequestDTO request) {
        Users admin = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AdminServiceException("Admin not found"));

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {
            throw new AdminServiceException("Not an admin");
        }

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new AdminServiceException("Password did not match");
        }

        return admin;
    }

    // Admin dashboard view
    public List<AdminUserDTO> getDashboardData() {
        List<Users> users = userRepository.findAll();

        return users.stream()
                .filter(user -> !"ADMIN".equalsIgnoreCase(user.getRole()))
                .map(user -> {
                    var medicines = medicineRepository.findByUserId(user.getId());
                    var medicineDTOs = medicines.stream().map(med ->
                            AdminUserDTO.MedicineDTO.builder()
                                    .medicineId(med.getId())
                                    .name(med.getName())
                                    .dosage(med.getDosage())
                                    .intakeTime(med.getIntakeTime() != null ? med.getIntakeTime().toString() : null)
                                    .build()
                    ).collect(Collectors.toList());

                    var bodyMetrics = bodyMetricsRepository.findByUserId(user.getId());
                    var bodyMetricDTOs = bodyMetrics.stream().map(metrics ->
                            AdminBodyMetricDTO.builder()
                                    .id(metrics.getUserId())
                                    .notes(metrics.getNotes())
                                    .bloodPressure(metrics.getBloodPressure())
                                    .temperature(String.valueOf(metrics.getTemperature()))
                                    .weight(String.valueOf(metrics.getWeight()))
                                    .build()
                    ).collect(Collectors.toList());

                    return AdminUserDTO.builder()
                            .userId(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .medicines(medicineDTOs)
                            .bodyMetrics(bodyMetricDTOs)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
