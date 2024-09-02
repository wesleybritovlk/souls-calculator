package com.wesleybritovlk.souls_calculator_api.app.user;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;

    public UserEntity save(UserRequest.Create request) {
        UserEntity entity = mapper.toEntity(request);
        return repository.saveAndFlush(entity);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return repository.findByEmailAndDeletedAtNull(email);
    }
}
