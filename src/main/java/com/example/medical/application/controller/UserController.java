package com.example.medical.application.controller;
import com.example.medical.application.user.dtos.LoginDTO;
import com.example.medical.application.user.dtos.UserRequestDTO;
import com.example.medical.application.user.dtos.UserResponseDTO;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
private final UserRepository userRepository;
private final JwtUtil jwtUtil;
private final PasswordEncoder passwordEncoder;
// login for user
@PostMapping("/signup")

public ResponseEntity<UserResponseDTO> signup(@RequestBody UserRequestDTO dto){
    // check if user already exits
    if (userRepository.findByEmail(dto.getEmail()).isPresent()){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
    Users user=Users.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(passwordEncoder.encode(dto.getPassword()))
            .role("USER")
            .age(dto.getAge())
            .gender(dto.getGender())
            .createdAt(Instant.now())
            .build();
    Users saveUser=userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(saveUser));
}

//login
    @PostMapping("/login")
public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO logindto){
    Users user=userRepository.findByEmail(logindto.getEmail())
            .orElseThrow(()-> new RuntimeException("user not found"));
    if(!passwordEncoder.matches(logindto.getPassword(),user.getPassword())){
        throw new  RuntimeException("Password did not match");
    }
    UserResponseDTO response= new UserResponseDTO(user);
    String token= jwtUtil.generateToken(user.getEmail(), user.getPassword());
    response.setToken(token);
    return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@RequestHeader("Authorization")String authHeader){
    String token=authHeader.replace("Bearer","").trim();
    // extract email from jwt
        String email=jwtUtil.ExtractEmail(token);

        // find user by email
        Users user=userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));
        return  ResponseEntity.ok(new UserResponseDTO(user));
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
    return ResponseEntity.ok(userRepository.findAll());
    }

}
