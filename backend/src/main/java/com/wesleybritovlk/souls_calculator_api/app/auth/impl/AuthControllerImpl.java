package com.wesleybritovlk.souls_calculator_api.app.auth.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.auth.AuthController;
import com.wesleybritovlk.souls_calculator_api.app.auth.AuthService;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResource;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Data;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService service;

    @PostMapping("login")
    public ResponseEntity<MessageData<AuthResponse>> login(@Valid @RequestBody AuthRequest.Login request) {
        AuthResponse response = service.login(request);
        var resource = CommonResource.toMessage("User logged successfuly.", response);
        return ResponseEntity.ok(resource);
    }

    @PostMapping("register")
    public ResponseEntity<MessageData<AuthResponse>> register(@Valid @RequestBody AuthRequest.Register request) {
        AuthResponse response = service.register(request);
        var resource = CommonResource.toMessage("User registered successfuly.", response);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("refresh_token")
    public ResponseEntity<Data<AuthResponse>> refreshToken(JwtAuthenticationToken token) {
        AuthResponse response = service.refreshToken(token);
        var resource = CommonResource.toData(response);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("logout")
    public ResponseEntity<Void> logout(JwtAuthenticationToken token) {
        service.logout(token);
        return ResponseEntity.noContent().build();
    }
}
