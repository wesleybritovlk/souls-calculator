package com.wesleybritovlk.souls_calculator_api.core.games.darksouls;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.dto.DarkSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonModel.SoulsNext;

public interface DarkSouls {
    SoulsNext calculateSouls(Souls request);
}
