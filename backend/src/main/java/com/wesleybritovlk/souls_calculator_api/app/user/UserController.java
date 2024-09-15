package com.wesleybritovlk.souls_calculator_api.app.user;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface UserController {
    ResponseEntity<Map> get(JwtAuthenticationToken token);
}
