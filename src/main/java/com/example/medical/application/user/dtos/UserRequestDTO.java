package com.example.medical.application.user.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequestDTO {
@NotBlank(message = "Name is required")
    private String name;
@Email(message = "Email must be valid")
@NotBlank(message = "email cannot be blank")
private String email;
@NotBlank(message = "password cannot be blanck")
@Size(min=8,message = "password must be 8 letters")
private String password;
private String role;

@NotBlank(message = "age cannot be blank")

private Integer age;
    @NotBlank(message = "age cannot be blank")

private String gender;

}
