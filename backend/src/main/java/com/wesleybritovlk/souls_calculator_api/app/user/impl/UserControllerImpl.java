package com.wesleybritovlk.souls_calculator_api.app.user.impl;

import static com.wesleybritovlk.souls_calculator_api.core.common.CommonResource.toData;
import static com.wesleybritovlk.souls_calculator_api.core.common.CommonResource.toMessage;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.user.UserController;
import com.wesleybritovlk.souls_calculator_api.app.user.UserService;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<Map> get(JwtAuthenticationToken token) {
        var response = service.find(token);
        var resource = toData(response);
        return ResponseEntity.ok(resource);
    }

    @PutMapping
    public ResponseEntity<Map> update(JwtAuthenticationToken token, @RequestBody @Valid UserRequest.Update request) {
        var response = service.update(token, request);
        var resource = toMessage("User updated successfuly", response);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(JwtAuthenticationToken token) {
        service.delete(token);
        return ResponseEntity.noContent().build();
    }
}
