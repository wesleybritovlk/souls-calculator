package com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.impl;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.round;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.DemonSouls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.model.DemonSoulsModel.SoulsNext;

@Component
public class DemonSoulsImpl implements DemonSouls {

    private static double calculateOneToEleven(int level) {
        double weightOne = 17d;
        double weightSeven = 18;
        double baseOne = 673, baseSeven = 793;
        if (level >= 2 && level < 8) {
            return baseOne + (level - 2) * weightOne;
        }
        if (level >= 8 && level < 12) {
            return baseSeven + (level - 8) * weightSeven;
        }
        return 0d;
    }

    private static double calculateAfterElevenSoulsForumla(int level) {
        return 0.02 * pow(level, 3) + 3.06 * pow(level, 2) + 105.6 * level - 895;
    }

    @Override
    public SoulsNext calculateSouls(Souls request) {
        int arrowLevel = request.arrowLevel();
        long currentSouls = request.currentSouls();
        long soulsNext = 0;
        double soulsAmount = 0d;
        for (int level = 1; level <= arrowLevel; ++level) {
            if (level >= 1 && level < 12)
                soulsAmount += calculateOneToEleven(level);
            else
                soulsAmount += calculateAfterElevenSoulsForumla(level);
        }
        soulsNext = round(soulsAmount);
        if (soulsNext < currentSouls)
            return new SoulsNext(soulsNext);
        if (soulsNext >= currentSouls)
            soulsNext -= currentSouls;
        return new SoulsNext(soulsNext);
    }

}
