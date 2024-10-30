package com.wesleybritovlk.souls_calculator_api.core.games.darksouls2;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.dto.DarkSouls2Request.Souls;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonModel.SoulsNext;

public interface DarkSouls2 {
    SoulsNext calculateSouls(Souls request);
}
