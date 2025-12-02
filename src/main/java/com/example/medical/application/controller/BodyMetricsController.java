package com.example.medical.application.controller;
import com.example.medical.application.bodymetrics.dtos.BodyMetricsRequestDTO;
import com.example.medical.application.bodymetrics.dtos.BodyMetricsResponseDTO;
import com.example.medical.application.bodymetrics.usecase.AddBodyMetricsUseCase;
import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.BodyMetricsRepository;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bodyMetrics")
public class BodyMetricsController {
private  final BodyMetricsRepository bodyMetricsRepository;
private final UserRepository userRepository;
private final JwtUtil jwtUtil;
private final AddBodyMetricsUseCase addBodyMetricsUseCase;
//@PostMapping
//    public ResponseEntity<BodyMetrics> addMetrics(@RequestBody BodyMetrics metrics){
//    return ResponseEntity.ok(bodyMetricsRepository.save(metrics));
//}

    @PostMapping
   public ResponseEntity<BodyMetricsResponseDTO> addMetrics(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody BodyMetricsRequestDTO dto){
        String token=authHeader.replace("Bearer","").trim();
        if(!jwtUtil.validateToken(token)){
            return ResponseEntity.status(401).build();
        }
        String email= jwtUtil.ExtractEmail(token);
        Users user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
        BodyMetrics metrics=addBodyMetricsUseCase.execute(user,dto);

        return ResponseEntity.ok(new BodyMetricsResponseDTO(metrics));

    }


@GetMapping("/{id}")
    public ResponseEntity<BodyMetrics> getById(@PathVariable Long id){
    return bodyMetricsRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BodyMetrics>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bodyMetricsRepository.findByUserId(userId));
    }
}
