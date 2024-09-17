package com.wesleybritovlk.souls_calculator_api.core.common;

import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonModel {
    public record SoulsNext(BigInteger soulsNext) {
    }
}
