package com.example.medical.application.email;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private JavaMailSender javaMailSender;
@Async
    public  void sendEmail(String to,String subject, String text){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("no-reply@medical.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
