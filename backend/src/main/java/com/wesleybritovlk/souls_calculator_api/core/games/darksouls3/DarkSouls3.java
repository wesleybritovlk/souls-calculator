package com.wesleybritovlk.souls_calculator_api.core.games.darksouls3;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto.DarkSouls3Request.Souls;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonModel.SoulsNext;

public interface DarkSouls3 {
    SoulsNext calculateSouls(Souls request);
}
