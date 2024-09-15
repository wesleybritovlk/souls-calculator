package com.wesleybritovlk.souls_calculator_api.app.auth;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest.Login request);

    AuthResponse register(AuthRequest.Register request);

    AuthResponse refreshToken(JwtAuthenticationToken token);

    void logout(JwtAuthenticationToken token);
}
