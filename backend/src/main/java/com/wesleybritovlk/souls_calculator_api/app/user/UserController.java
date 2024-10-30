package com.wesleybritovlk.souls_calculator_api.app.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse.Full;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse.Update;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Data;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "User profile operations")
public interface UserController {
    ResponseEntity<Data<Full>> get(JwtAuthenticationToken token);

    ResponseEntity<MessageData<Update>> update(JwtAuthenticationToken token, UserRequest.Update request);

    ResponseEntity<Void> delete(JwtAuthenticationToken token);
}
