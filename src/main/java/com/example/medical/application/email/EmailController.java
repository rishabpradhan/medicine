package com.example.medical.application.email;

import com.example.medical.domain.repository.UserRepository;
import com.example.medical.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private  final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> sendEmail( @RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer", "").trim();
        if(!jwtUtil.validateToken(token)){
            return ResponseEntity.status(401).build();
        }
        String email=jwtUtil.ExtractEmail(token);
        emailService.sendEmail(email,"Reminder","take your medicine");

        return ResponseEntity.ok("Successfully sent the email");
    }


}
