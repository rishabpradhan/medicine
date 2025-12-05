package com.example.medical.domain.service;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users createUser(String email, String password, String role, String user, String dtoRole, Integer age , String gender){
        Users users=Users.builder()
                .id(null)
                .email(email)
                .password(password)
                .role(role)
                .age(age)
                .gender(gender)
                .createdAt(Instant.now())
                .build();
        return  userRepository.save(users);
    }
    public Optional<Users> getUsersById(Long id){
        return userRepository.findById(id);
    }
    public List<Users> getAllUsers(){
        return  userRepository.findAll();
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
