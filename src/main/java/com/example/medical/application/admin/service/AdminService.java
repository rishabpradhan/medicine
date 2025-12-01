package com.example.medical.application.admin.service;

import com.example.medical.application.admin.dtos.AdminLoginRequestDTO;
import com.example.medical.application.admin.dtos.AdminUserDTO;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.MedicineRepository;
import com.example.medical.domain.repository.UserRepository;

import com.example.medical.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AdminService {
    private final UserRepository userRepository;
    private  final MedicineRepository medicineRepository;
    private  final PasswordEncoder passwordEncoder;



    public Users login(AdminLoginRequestDTO request){
        Users admin = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {
            throw new RuntimeException("Not an admin");
        }

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Password did not match");
        }

        return admin;
    }

    // admin view

    public List<AdminUserDTO> getDashboardData(){
        List<Users> users=userRepository.findAll();
        return users.stream()
                .filter(user->!"ADMIN".equalsIgnoreCase(user.getRole()) )
                .map(user -> {
            var medicines=medicineRepository.findByUserId(user.getId());

            var medicineDTOs = medicines.stream().map(med -> AdminUserDTO.MedicineDTO.builder()
                    .medicineId(med.getId())
                    .name(med.getName())
                    .dosage(med.getDosage())
                    .IntakeTime(med.getIntakeTime() != null ? med.getIntakeTime().toString() : null)
                    .build()).collect(Collectors.toList());

            return AdminUserDTO.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .medicines(medicineDTOs)
                    .build();
        }).collect(Collectors.toList());
    }

}
