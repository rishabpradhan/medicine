package com.example.medical.application.controller;
import com.example.medical.domain.model.SideEffect;
import com.example.medical.domain.repository.SideEffectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sideEffect")
public class SideEffectController {
    private final SideEffectRepository sideEffectRepository;

    @PostMapping
    public ResponseEntity<SideEffect> addSideEffect(@RequestBody SideEffect sideEffect){
        return ResponseEntity.ok(sideEffectRepository.save(sideEffect));
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
