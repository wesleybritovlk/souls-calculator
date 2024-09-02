package com.wesleybritovlk.souls_calculator_api.app.auth.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthRequest {
        @Validated
        public record Login(
                        @NotNull String email,
                        @NotNull String password) {
        }

        @Validated
        public record Register(
                        @NotNull String username,
                        @NotNull @Email String email,
                        @NotNull @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\W)(?=.*\\d)[\\w\\d\\W]{8,32}$", 
                        message = "Invalid password, minimum of eight chars, one number, one special character and one capital letter") String password) {
        }
}
