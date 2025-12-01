package com.example.medical.application.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter

public class LoginDTO {
    @Email(message = "Email must be valid ")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
}
