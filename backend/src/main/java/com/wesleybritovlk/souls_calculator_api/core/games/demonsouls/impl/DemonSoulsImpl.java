package com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.impl;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonModel.SoulsNext;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.DemonSouls;

@Component
public class DemonSoulsImpl implements DemonSouls {

    private static BigDecimal decimal(Number num) {
        return BigDecimal.valueOf(num.doubleValue());
    }

    private static final BigDecimal TWELVE = decimal(12);

    private static final int[] START_SOULS = new int[] {
            0, 673, 690, 707, 724, 741, 758, 775, 793, 811, 829, 847 };

    private static BigDecimal selectOneToTwelve(BigDecimal level) {
        return decimal(START_SOULS[level.subtract(ONE).intValue()]);
    }

    private static BigDecimal calculateWithSoulsForumla(BigDecimal level) {
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
            if (level.compareTo(ONE) >= 0 && level.compareTo(TWELVE) <= 0)
                amount = amount.add(selectOneToTwelve(level));
            else
                amount = amount.add(calculateWithSoulsForumla(level));
        }
        BigInteger soulsNext = amount.setScale(0, HALF_EVEN).toBigIntegerExact(),
                currentSouls = request.currentSouls();
        if (soulsNext.compareTo(currentSouls) <= 0)
            return new SoulsNext(BigInteger.ZERO);
        if (soulsNext.compareTo(currentSouls) >= 0)
            soulsNext = soulsNext.subtract(currentSouls);
        return new SoulsNext(soulsNext);
    }

}
