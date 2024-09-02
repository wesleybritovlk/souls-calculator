package com.wesleybritovlk.souls_calculator_api.app.user.dto;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    @Builder
    public record Full(
            UUID id,
            String username,
            String email) {
    }
}
