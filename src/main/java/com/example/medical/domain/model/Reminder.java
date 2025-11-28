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
public class Reminder {
    private Long id;
    private boolean active;
    private Instant remindAt;
    private Long medicineId;

}
