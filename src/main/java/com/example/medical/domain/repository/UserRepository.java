package com.example.medical.domain.repository;
import com.example.medical.domain.model.Users;

import java.util.*;
public interface UserRepository {
Users save(Users users);
Optional<Users> findById(Long id);
Optional<Users> findByEmail(String email);
List<Users> findAll();
 void deleteById(Long id);
}
