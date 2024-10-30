package com.wesleybritovlk.souls_calculator_api.app.user;

import java.util.Optional;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse;

public interface UserService {

    UserEntity save(UserRequest.Create request);

    Optional<UserProjection.Auth> findAuth(String email);

    UserResponse.Full find(JwtAuthenticationToken token);

    UserResponse.Update update(JwtAuthenticationToken token, UserRequest.Update request);

    void delete(JwtAuthenticationToken token);
}
