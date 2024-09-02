package com.wesleybritovlk.souls_calculator_api.app.auth;

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

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthTokenHelper {
    private final JwtEncoder jwtEncoder;

    @Value("${app.jwt.expires-in}")
    private Long expiresIn;

    public AuthResponse generateToken(AuthPayload.Token payload) {
        var roles = payload.authorities().stream()
                .map(v -> v.getAuthority().replaceAll("SCOPE_", ""))
                .collect(Collectors.toSet());
        String token = createToken(payload.id(), payload.subject(), Map.of("scope", roles));
        return AuthResponse.builder().accessToken(token).expiresIn(expiresIn).build();
    }

    private String createToken(UUID id, String subject, Map<String, Object> additionalClaims) {
        var now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .id(id.toString())
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claims(claim -> claim.putAll(additionalClaims))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
