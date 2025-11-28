package com.example.medical.application.admin.controller;

import com.example.medical.application.admin.dtos.AdminLoginRequestDTO;
import com.example.medical.application.admin.dtos.AdminUserDTO;
import com.example.medical.application.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequestDTO request) {
        adminService.login(request); // returns admin user if correct
        return ResponseEntity.ok("Login successful"); // later you can return JWT token
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<AdminUserDTO>> dashboard() {
        List<AdminUserDTO> data = adminService.getDashboardData();
        return ResponseEntity.ok(data);
    }
}
