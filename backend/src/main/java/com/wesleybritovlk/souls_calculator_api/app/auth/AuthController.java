package com.wesleybritovlk.souls_calculator_api.app.auth;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("login")
    public ResponseEntity<Map> login(@Valid @RequestBody AuthRequest.Login request) {
        AuthResponse response = service.login(request);
        var resource = new LinkedHashMap<>();
        resource.put("message", "User logged successfuly.");
        resource.put("data", response);
        return ResponseEntity.ok(resource);
    }

    @PostMapping("register")
    public ResponseEntity<Map> register(@Valid @RequestBody AuthRequest.Register request) {
        AuthResponse response = service.register(request);
        var resource = new LinkedHashMap<>();
        resource.put("message", "User registered successfuly.");
        resource.put("data", response);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("refresh_token")
    public ResponseEntity<Map> register(JwtAuthenticationToken token) {
        AuthResponse response = service.refreshToken(token);
        return ResponseEntity.ok(Map.of("data", response));
    }
}
