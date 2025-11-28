package com.example.medical.application.user.dtos;

import com.example.medical.domain.model.Users;
import lombok.Data;

@Data
public class UserResponseDTO {
private Long id;
private String name;
private String email;
    private String role;

    public UserResponseDTO(Users users){
        this.id=users.getId();
        this.name=users.getName();
        this.email=users.getEmail();
        this.role=users.getRole();
    }

}
