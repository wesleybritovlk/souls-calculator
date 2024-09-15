package com.wesleybritovlk.souls_calculator_api.infra.util.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
        public static final String USER_NOT_FOUND = "User " + HttpStatus.NOT_FOUND.getReasonPhrase();
        public static final String FALIED_UPDATE = "Falied to update " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        public static final String FALIED_DELETE = "Failed to delete " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        public static final String CONFLICT_USER = "User already exists " + HttpStatus.CONFLICT.getReasonPhrase();
        public static final String UNAUTHORIZED_TOKEN = "Full authentication is required to access this resource";

}