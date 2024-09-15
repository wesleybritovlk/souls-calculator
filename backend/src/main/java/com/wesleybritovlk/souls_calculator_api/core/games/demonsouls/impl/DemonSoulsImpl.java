package com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.impl;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.round;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.DemonSouls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.model.DemonSoulsModel.SoulsNext;

@Component
public class DemonSoulsImpl implements DemonSouls {

    private static long calculateOneToEleven(int arrowLevel) {
        double weightOne = 17d;
        double weightSeven = 18;
        int baseOne = 673, baseSeven = 793;
        double souls = 0;
        if (arrowLevel >= 2 && arrowLevel < 8) {
            souls = baseOne + (arrowLevel - 2) * weightOne;
            return round(souls);
        }
        if (arrowLevel >= 8 && arrowLevel < 12) {
            souls = baseSeven + (arrowLevel - 8) * weightSeven;
            return round(souls);
        }
        return 0;
    }

    private static long calculateAfterElevenSoulsForumla(int arrowLevel) {
        double souls = 0.02 * pow(arrowLevel, 3) + 3.06 * pow(arrowLevel, 2) + 105.6 * arrowLevel - 895;
        return round(souls);
    }

    @Override
    public SoulsNext calculateSouls(Souls request) {
        long currentSouls = request.currentSouls();
        int arrowLevel = request.arrowLevel();
        long soulsNext = 0;
        if (arrowLevel >= 1 && arrowLevel < 12)
            soulsNext += calculateOneToEleven(arrowLevel);
        else
            soulsNext += calculateAfterElevenSoulsForumla(arrowLevel);
        if (soulsNext >= currentSouls)
            soulsNext -= currentSouls;
        if (soulsNext < currentSouls)
            soulsNext = 0;
        return new SoulsNext(soulsNext);
    }

}
