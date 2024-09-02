package com.wesleybritovlk.souls_calculator_api.infra.filter;

import java.io.IOException;
import java.time.ZonedDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesleybritovlk.souls_calculator_api.infra.util.exception.ExceptionHandlerResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AccessDeniedFilter implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ExceptionHandlerResponse exResponse = ExceptionHandlerResponse.builder()
                .timestamp(String.valueOf(ZonedDateTime.now()))
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(accessDeniedException.getMessage())
                .requestPath(request.getRequestURI())
                .build();
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(exResponse));
    }
}