package com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.impl;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TWO;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.DemonSouls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.model.DemonSoulsModel.SoulsNext;

@Component
public class DemonSoulsImpl implements DemonSouls {

    private static BigDecimal decimal(Number num) {
        return BigDecimal.valueOf(num.doubleValue());
    }

    private static BigInteger bigInt(Number num) {
        return BigInteger.valueOf(num.longValue());
    }

    private static final BigDecimal EIGHT = decimal(8);
    private static final BigDecimal NINE = decimal(9);
    private static final BigDecimal TWELVE = decimal(12);

    private static BigDecimal calculateOneToEleven(BigDecimal level) {
        if (level.compareTo(TWO) >= 0 && level.compareTo(EIGHT) < 0) {
            BigDecimal diff = level.subtract(TWO), weight = decimal(17), constant = decimal(673);
            return weight.multiply(diff).add(constant);
        }
        if (level.compareTo(EIGHT) >= 0 && level.compareTo(TWELVE) < 0) {
            BigDecimal diff = level.subtract(NINE), weight = decimal(18), constant = decimal(793);
            return weight.multiply(diff).add(constant);
        }
        return ZERO;
    }

    private static BigDecimal calculateAfterElevenSoulsForumla(BigDecimal level) {
        BigDecimal firstCoeff = decimal(0.02), secondCoeff = decimal(3.06),
                thirdCoeff = decimal(105.6), constant = decimal(895);
        return firstCoeff.multiply(level.pow(3))
                .add(secondCoeff.multiply(level.pow(2)))
                .add(thirdCoeff.multiply(level))
                .subtract(constant);
    }

    @Override
    public SoulsNext calculateSouls(Souls request) {
        BigDecimal arrowLevel = decimal(request.arrowLevel()), amount = ZERO;
        for (BigDecimal level = ONE; level.compareTo(arrowLevel) <= 0; level = level.add(ONE)) {
            if (level.compareTo(ONE) >= 0 && level.compareTo(TWELVE) < 0)
                amount = amount.add(calculateOneToEleven(level));
            else
                amount = amount.add(calculateAfterElevenSoulsForumla(level));
        }
        BigInteger soulsNext = amount.setScale(0, HALF_EVEN).toBigIntegerExact(),
                currentSouls = bigInt(request.currentSouls());
        if (soulsNext.compareTo(currentSouls) < 0)
            return new SoulsNext(soulsNext);
        if (soulsNext.compareTo(currentSouls) >= 0)
            soulsNext = soulsNext.subtract(currentSouls);
        return new SoulsNext(soulsNext);
    }

}
