package com.example.medical.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "reminders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReminderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "remind_at", nullable = false)
    private Instant remindAt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_reminder_medicine"))
    private MedicineEntity medicine;
}
