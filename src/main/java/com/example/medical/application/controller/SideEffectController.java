package com.example.medical.application.controller;
import com.example.medical.application.sideeffect.dtos.SideEffectRequestDTO;
import com.example.medical.application.sideeffect.dtos.SideEffectResponseDTO;
import com.example.medical.application.sideeffect.usecase.AddSideEffectUseCase;
import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.SideEffect;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.MedicineRepository;
import com.example.medical.domain.repository.SideEffectRepository;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sideEffect")
public class SideEffectController {
    private final SideEffectRepository sideEffectRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AddSideEffectUseCase addSideEffectUseCase;
    private final MedicineRepository medicineRepository;

    @PostMapping
    public ResponseEntity<SideEffectResponseDTO> addSideEffect(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody SideEffectRequestDTO dto) {

        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }

        String email = jwtUtil.ExtractEmail(token);
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Medicine medicine=medicineRepository.findById(dto.getMedicineId()).orElseThrow(()->new RuntimeException("medicine not found"));
        SideEffect sideEffect = addSideEffectUseCase.execute(user,medicine, dto);



        return ResponseEntity.ok(new SideEffectResponseDTO(sideEffect));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SideEffect> getById(@PathVariable Long id){
        return sideEffectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("userId{userId}")
    public ResponseEntity<List<SideEffect>> getByUser(@PathVariable Long userId){
        return ResponseEntity.ok(sideEffectRepository.findByUserId(userId));
    }

}
