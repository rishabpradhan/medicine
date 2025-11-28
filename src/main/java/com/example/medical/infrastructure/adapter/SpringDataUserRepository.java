package com.example.medical.infrastructure.adapter;
import com.example.medical.domain.model.Users;
import com.example.medical.domain.repository.UserRepository;
import com.example.medical.infrastructure.mapper.UserMapper;
import com.example.medical.infrastructure.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataUserRepository implements UserRepository {
    private final UserJpaRepository jpaRepository;

    public Users save(Users users){
        var entity= UserMapper.toEntity(users);
        entity=jpaRepository.save(entity);
        return UserMapper.toDomain(entity);

    }
    public Optional<Users> findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserMapper::toDomain);
    }
    @Override
    public Optional<Users> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserMapper::toDomain);
    }
    public List<Users> findAll() {
        return jpaRepository.findAll().stream()
                .map(UserMapper::toDomain)
                .toList();
    }
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }



}
