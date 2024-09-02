package com.wesleybritovlk.souls_calculator_api.app.user.dto;

import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {
    @Builder
    public record Create(
            String username,
            String email,
            String password,
            RoleEntity role) {
    }
}
