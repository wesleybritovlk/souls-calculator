package com.wesleybritovlk.souls_calculator_api.core.common;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponse {
        public record Message(
                        @Schema(example = "Message described in Response status - Description.") String message) {
        }

        public record MessageData<T>(
                        @Schema(example = "Message described in Response status - Description.") String message,
                        T data) {
        }

        public record Data<T>(T data) {
        }

        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Page<T>(
                        List<T> data,
                        int currentPage,
                        int pageSize,
                        int totalPages,
                        long totalElements,
                        boolean isFirst,
                        boolean isLast,
                        boolean empty) {
        }

        @Schema(name = "CommonResponseSouls", title = "CommonResponseSouls")
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Souls(
                        BigInteger soulsNext) {
        }
}
