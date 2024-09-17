package com.wesleybritovlk.souls_calculator_api.app.games.darksouls3;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto.DarkSouls3Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

public interface DarkSouls3Service {
    Souls calculateSouls(DarkSouls3Request.Souls request);
}
