package com.example.medical.application.reminder.usecase;


import com.example.medical.application.reminder.dtos.ReminderRequestDTO;
import com.example.medical.domain.model.Reminder;
import com.example.medical.domain.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddReminderUseCase {
    private final ReminderService reminderService;

    public Reminder execute(ReminderRequestDTO dto){
        return reminderService.addReminder(dto.getMedicineId(),  dto.getRemindAt(), dto.getActive());

    }


}
