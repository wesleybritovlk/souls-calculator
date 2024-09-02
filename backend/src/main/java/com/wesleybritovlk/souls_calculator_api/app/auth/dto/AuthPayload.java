package com.wesleybritovlk.souls_calculator_api.app.auth.dto;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthPayload {
    @Builder
    public record Token(
            UUID id,
            String subject,
            Collection<GrantedAuthority> authorities) {
    }
}
