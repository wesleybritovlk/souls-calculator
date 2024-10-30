package com.wesleybritovlk.souls_calculator_api.app.games.darksouls2;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.dto.DarkSouls2Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

public interface DarkSouls2Service {
    Souls calculateSouls(DarkSouls2Request.Souls request);
}
