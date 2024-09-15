package com.wesleybritovlk.souls_calculator_api.app.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AuthResponse(
                String accessToken,
                Long expiresIn) {
}
