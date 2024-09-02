package com.wesleybritovlk.souls_calculator_api.infra.util.exception;

import lombok.Builder;

@Builder
public record ExceptionHandlerResponse(
        String timestamp,
        int status,
        String error,
        String message,
        String requestPath) {
}