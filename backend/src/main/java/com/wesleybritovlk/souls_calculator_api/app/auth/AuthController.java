package com.wesleybritovlk.souls_calculator_api.app.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Data;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "Authentication operations")
public interface AuthController {
    ResponseEntity<MessageData<AuthResponse>> login(AuthRequest.Login request);

    ResponseEntity<MessageData<AuthResponse>> register(AuthRequest.Register request);

    ResponseEntity<Data<AuthResponse>> refreshToken(JwtAuthenticationToken token);

    ResponseEntity<Void> logout(JwtAuthenticationToken token);
}
