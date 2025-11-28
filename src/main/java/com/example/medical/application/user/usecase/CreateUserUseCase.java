package com.example.medical.application.user.usecase;

import com.example.medical.application.user.dtos.UserRequestDTO;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
private final UserService userService;
public Users execute(UserRequestDTO dto){
    return userService.createUser(
            dto.getName(),
            dto.getEmail(),
            dto.getPassword(),
            dto.getRole(),
            dto.getAge(),
            dto.getGender()
    );
}
}
