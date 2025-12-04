package com.example.medical.application.medicine.usecase;

import com.example.medical.application.medicine.dtos.MedicineRequestDTO;
import com.example.medical.domain.model.Medicine;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddMedicineUseCase {
    private final MedicineService medicineService;

    public Medicine execute (Users users,MedicineRequestDTO dto){
        return medicineService.addMedicine(
                users,
                dto.getName(),
                dto.getDosage(),
                dto.getIntakeTime(),
                dto.getNotes(),
                dto.getActive(),
                users.getId()


        );


    }

}
