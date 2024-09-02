package com.wesleybritovlk.souls_calculator_api.app.user;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;

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
}
