package com.wesleybritovlk.souls_calculator_api.infra.filter;

import java.io.IOException;
import java.time.ZonedDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesleybritovlk.souls_calculator_api.infra.util.exception.ExceptionHandlerResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthEntryPointFilter implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionHandlerResponse exResponse = ExceptionHandlerResponse.builder()
                .timestamp(String.valueOf(ZonedDateTime.now()))
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(authException.getMessage())
                .requestPath(request.getRequestURI())
                .build();
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(exResponse));
    }

}