package com.example.medical.application.controller;

import com.example.medical.application.medicine.dtos.MedicineRequestDTO;
import com.example.medical.application.medicine.dtos.MedicineResponseDTO;
import com.example.medical.application.medicine.usecase.AddMedicineUseCase;
import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.MedicineRepository;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicine")
public class MedicineController {
private final MedicineRepository medicineRepository;
private final UserRepository userRepository;
private final JwtUtil jwtUtil;
private final AddMedicineUseCase addMedicineUseCase;
    @PostMapping
//    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
//        Medicine saved = medicineRepository.save(medicine);
//        return ResponseEntity.ok(saved);
//    }

    public ResponseEntity<MedicineResponseDTO> addMedicine(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody MedicineRequestDTO dto){
        String token=authHeader.replace("Bearer","").trim();
        if(!jwtUtil.validateToken(token)){
            return  ResponseEntity.status(401).build();
        }
        String email= jwtUtil.ExtractEmail(token);
        Users user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        Medicine medicine=addMedicineUseCase.execute(user,dto);

        return ResponseEntity.ok(new MedicineResponseDTO(medicine));

    }


    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        return medicineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Medicine>> getMedicinesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineRepository.findByUserId(userId));
    }
}
