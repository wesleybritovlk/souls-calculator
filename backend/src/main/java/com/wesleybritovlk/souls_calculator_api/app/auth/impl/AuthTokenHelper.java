package com.wesleybritovlk.souls_calculator_api.app.auth.impl;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthPayload;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;
import com.wesleybritovlk.souls_calculator_api.core.uuidmask.UUIDMask;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokenHelper {
    private final JwtEncoder jwtEncoder;
    private final UUIDMask mask;

    @Value("${app.global.ttl}")
    private Long ttlSeconds;

    private String createToken(UUID id, String subject, Map<String, Object> additionalClaims) {
        var now = Instant.now();
        String maskedId = mask.setMaskedId(id);
        var claims = JwtClaimsSet.builder()
                .id(maskedId)
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(ttlSeconds))
                .claims(claim -> claim.putAll(additionalClaims))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public AuthResponse generateToken(AuthPayload.Token payload) {
        var roles = payload.authorities().stream()
                .map(v -> v.getAuthority().replaceAll("SCOPE_", ""))
                .collect(Collectors.toSet());
        String token = createToken(payload.id(), payload.subject(), Map.of("scope", roles));
        return AuthResponse.builder().accessToken(token).expiresIn(ttlSeconds).build();
    }
}
