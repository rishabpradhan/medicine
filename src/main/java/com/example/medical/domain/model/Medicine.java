package com.example.medical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicine {
private Long id;
private boolean active;
private Instant createdAt;
private String dosage;
private LocalTime intakeTime;
private String name;
private  String notes;
private Long userId;

private List<Reminder> reminders;
private List<SideEffect> sideEffects;
}

