package com.example.medical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SideEffect {
private Long id;
private Instant date;
private String effect;
private Long medicineId;
private Long userId;
}
