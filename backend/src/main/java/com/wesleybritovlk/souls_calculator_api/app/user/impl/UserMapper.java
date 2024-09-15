package com.wesleybritovlk.souls_calculator_api.app.user.impl;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.user.UserEntity;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse.Update;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {
    protected UserEntity toEntity(UserRequest.Create request) {
        return UserEntity.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .roles(Set.of(request.role()))
                .build();
    }

    protected UserResponse.Full toResponse(UserEntity entity) {
        return UserResponse.Full.builder()
                .createdAt(entity.getCreatedAt().toLocalDate())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }

    public Update toResponse(UserRequest.Update request) {
        return UserResponse.Update.builder()
                .username(request.username())
                .email(request.email())
                .build();
    }
}
