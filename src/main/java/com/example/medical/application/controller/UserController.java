package com.example.medical.application.controller;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
private final UserRepository userRepository;
// login for user
@PostMapping
public ResponseEntity<Users> createUser(@RequestBody Users user) {

    if (user.getRole() == null) {
        user.setRole("USER"); // default role for normal users
    }
    if (user.getCreatedAt() == null) {
        user.setCreatedAt(Instant.now()); // default creation timestamp
    }
    Users savedUser = userRepository.save(user);

    return ResponseEntity.ok(savedUser);
}
//login for admin

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
    return ResponseEntity.ok(userRepository.findAll());
    }

}
