package com.example.medical.application.sideeffect.dtos;

import com.example.medical.domain.model.SideEffect;
import lombok.Data;

import java.time.Instant;
@Data
public class SideEffectResponseDTO {
   private Long id;
   private Long userId;
   private Long medicineId;
   private String effect;
   private Instant date;

   public SideEffectResponseDTO(SideEffect sideEffect){
       this.id= sideEffect.getId();
       this.userId = sideEffect.getUserId();
       this.medicineId = sideEffect.getMedicineId();
       this.effect = sideEffect.getEffect();
       this.date = sideEffect.getDate();


   }
}
