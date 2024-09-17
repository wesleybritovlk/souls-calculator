package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemonSoulsResponse {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Souls(
            BigInteger soulsNext) {
    }
}
