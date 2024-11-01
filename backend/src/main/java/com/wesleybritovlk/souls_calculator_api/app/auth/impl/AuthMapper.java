package com.wesleybritovlk.souls_calculator_api.app.auth.impl;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthPayload;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthPayload.Token;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.user.UserEntity;
import com.wesleybritovlk.souls_calculator_api.app.user.UserProjection;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest.Create;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthMapper {
        protected Token toPayload(AuthRequest.Login request, Optional<UserProjection.Auth> projection) {
                return AuthPayload.Token.builder()
                                .id(projection.get().getId())
                                .subject(request.email())
                                .authorities(projection.get().getRoles().stream()
                                                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                                                .collect(Collectors.toSet()))
                                .build();
        }

        protected Token toPayload(AuthRequest.Register request, UserEntity entity) {
                return AuthPayload.Token.builder()
                                .id(entity.getId())
                                .subject(request.email())
                                .authorities(entity.getRoles().stream()
                                                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                                                .collect(Collectors.toSet()))
                                .build();
        }

        protected Token toPayload(UUID userId, JwtAuthenticationToken token) {
                return AuthPayload.Token.builder()
                                .id(userId)
                                .subject(token.getName())
                                .authorities(token.getAuthorities())
                                .build();
        }

        protected Create toRequest(AuthRequest.Register request, String password, RoleEntity role) {
                return UserRequest.Create.builder()
                                .username(request.username())
                                .email(request.email())
                                .password(password)
                                .role(role)
                                .build();
        }

}
