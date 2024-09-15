package com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemonSoulsModel {
    public record SoulsNext(Long soulsNext) {
    }
}
