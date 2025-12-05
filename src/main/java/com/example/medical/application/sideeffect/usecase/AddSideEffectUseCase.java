package com.example.medical.application.sideeffect.usecase;

import com.example.medical.application.sideeffect.dtos.SideEffectRequestDTO;
import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.SideEffect;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AddSideEffectUseCase {
    private final SideEffectService sideEffectService;
    public SideEffect execute(Users user, Medicine medicine,SideEffectRequestDTO dto) {
//        Users user,Long userId, Long medicineId, String effect, Instant date

        sideEffectService.addSideEffect(user, user.getId(), medicine, dto.getEffect(), Instant.now());

        return SideEffect.builder()
                .medicineId(medicine.getId())
                .userId(user.getId())
                .effect(dto.getEffect())
                .date(Instant.now())
                .build();
    }
}
