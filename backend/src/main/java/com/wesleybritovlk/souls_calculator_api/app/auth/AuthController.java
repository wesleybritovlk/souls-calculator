package com.wesleybritovlk.souls_calculator_api.app.auth;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;

public interface AuthController {
    ResponseEntity<Map> login(AuthRequest.Login request);

    ResponseEntity<Map> register(AuthRequest.Register request);

    ResponseEntity<Map> refreshToken(JwtAuthenticationToken token);

    ResponseEntity<Void> logout(JwtAuthenticationToken token);
}
