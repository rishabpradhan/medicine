package com.example.medical.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "side_effects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SideEffectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "effect", nullable = false, length = 255)
    private String effect;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id", foreignKey = @ForeignKey(name = "fk_sideeffect_medicine"))
    private MedicineEntity medicine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_sideeffect_user"))
    private UserEntity user;
}
