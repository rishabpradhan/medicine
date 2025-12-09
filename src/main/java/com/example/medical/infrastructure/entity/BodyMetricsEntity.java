package com.example.medical.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "body_metrics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_pressure", length = 255)
    private String bloodPressure;

    @Column(name = "date", nullable = false)
    private Instant date=Instant.now();

    @Column(length = 255)
    private String notes;

    @Column(name = "sugar_level")
    private Double sugarLevel;

    @Column
    private Double temperature;

    @Column
    private Double weight;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_bodymetrics_user"))
    private UserEntity user;
}
