package com.example.medical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    private Long id;
    private Integer age;
    private Instant createdAt;
    private String email;
    private String gender;
    private String name;
    private String password;
    private String role;

    private List<Medicine> medicines;
private List<SideEffect> sideEffects;
private List<BodyMetrics> bodyMetrics;

}
