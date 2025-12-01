package com.example.medical.application.admin.controller;

import com.example.medical.application.admin.dtos.AdminLoginRequestDTO;
import com.example.medical.application.admin.dtos.AdminUserDTO;
import com.example.medical.application.admin.service.AdminService;
import com.example.medical.domain.model.Users;
import com.example.medical.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AdminLoginRequestDTO request) {
        Users admin = adminService.login(request);

        String token = jwtUtil.generateToken(admin.getEmail(), admin.getRole());

        // Wrap token in JSON object
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

//    @GetMapping("/dashboard")
//    public ResponseEntity<List<AdminUserDTO>> dashboard() {
//        List<AdminUserDTO> data = adminService.getDashboardData();
//        return ResponseEntity.ok(data);
//    }
@GetMapping("/dashboard")
public ResponseEntity<List<AdminUserDTO>> dashboard(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    if (!jwtUtil.validateToken(token)) {
        return ResponseEntity.status(401).build();
    }
    return ResponseEntity.ok(adminService.getDashboardData());
}
}
