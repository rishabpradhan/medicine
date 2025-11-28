package com.example.medical.application.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/test")
    public  String sendEmail(){
        emailService.sendEmail("john@gmail.com","Reminder","take your medicine");
   return "Email sent sucessfully";
    }
}
