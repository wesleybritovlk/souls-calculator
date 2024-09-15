package com.wesleybritovlk.souls_calculator_api.app.user.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Full(
            LocalDate createdAt,
            String username,
            String email) {
    }

    @Builder
    public record Update(
            String username,
            String email) {
    }
}
