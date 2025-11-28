package com.example.medical.application.controller;
import com.example.medical.domain.model.BodyMetrics;
import com.example.medical.domain.repository.BodyMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bodyMetrics")
public class BodyMetricsController {
private  final BodyMetricsRepository bodyMetricsRepository;

@PostMapping
    public ResponseEntity<BodyMetrics> addMetrics(@RequestBody BodyMetrics metrics){
    return ResponseEntity.ok(bodyMetricsRepository.save(metrics));
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
