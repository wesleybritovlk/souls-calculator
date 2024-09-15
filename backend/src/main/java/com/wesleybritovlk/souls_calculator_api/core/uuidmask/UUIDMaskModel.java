package com.wesleybritovlk.souls_calculator_api.core.uuidmask;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;

@Builder
public record UUIDMaskModel(
        UUID userId,
        Instant iat,
        Instant exp) {
    public static UUIDMaskModel toModel(UUID userId, long ttlSeconds) {
        return UUIDMaskModel.builder()
                .userId(userId)
                .iat(Instant.now())
                .exp(Instant.now().plusSeconds(ttlSeconds))
                .build();
    }
}
