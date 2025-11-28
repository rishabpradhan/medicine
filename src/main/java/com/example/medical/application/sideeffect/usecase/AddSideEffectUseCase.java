package com.example.medical.application.sideeffect.usecase;

import com.example.medical.application.sideeffect.dtos.SideEffectRequestDTO;
import com.example.medical.domain.model.SideEffect;
import com.example.medical.domain.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddSideEffectUseCase {
    private SideEffectService sideEffectService;
    public SideEffect execute(SideEffectRequestDTO dto) {
        return sideEffectService.addSideEffect(dto.getUserId(), dto.getMedicineId(), dto.getEffect(), dto.getDate());
    }
}
