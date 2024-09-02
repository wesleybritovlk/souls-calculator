package com.wesleybritovlk.souls_calculator_api.app.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String accessToken,
        Long expiresIn) {
}
