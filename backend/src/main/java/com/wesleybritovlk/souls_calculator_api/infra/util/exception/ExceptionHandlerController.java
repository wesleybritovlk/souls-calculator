package com.wesleybritovlk.souls_calculator_api.infra.util.exception;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionHandlerResponse> handleMethodArgumentNotValidException(
                        MethodArgumentNotValidException ex, HttpServletRequest request) {
                HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
                List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
                String message = allErrors.stream().map(ObjectError::getDefaultMessage)
                                .collect(Collectors.joining(", "));
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), message, request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<ExceptionHandlerResponse> handleResponseStatusException(ResponseStatusException ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), ex.getReason(), request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ExceptionHandlerResponse> handleHttpMessageNotReadableException(
                        HttpMessageNotReadableException ex, HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(Throwable.class)
        public ResponseEntity<Object> handleUnexpectedException(Throwable ex, HttpServletRequest request) {
                HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionHandlerResponse> handleGeneralException(Exception ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.FORBIDDEN;
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(PropertyReferenceException.class)
        public ResponseEntity<ExceptionHandlerResponse> handlePropertyReferenceException(PropertyReferenceException ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ExceptionHandlerResponse> handleConstraintViolationException(
                        ConstraintViolationException ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                String message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                                .collect(Collectors.joining(", "));
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), message, request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public ResponseEntity<ExceptionHandlerResponse> handleMultipartException(MaxUploadSizeExceededException ex,
                        HttpServletRequest request) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                ExceptionHandlerResponse response = new ExceptionHandlerResponse(String.valueOf(ZonedDateTime.now()),
                                status.value(),
                                status.getReasonPhrase(), ex.getBody().getDetail(), request.getRequestURI());
                return ResponseEntity.status(status).body(response);
        }

}