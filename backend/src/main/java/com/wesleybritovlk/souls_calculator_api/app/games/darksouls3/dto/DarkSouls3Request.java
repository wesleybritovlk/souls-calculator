package com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DarkSouls3Request {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Souls(
            @NotNull @Min(value = 1, message = "level cannot be less than 1") @Max(value = 802, message = "level cannot be greater than 802") Integer arrowLevel,
            @NotNull @Min(value = 0, message = "souls cannot be negative") BigInteger currentSouls) {
    }
}