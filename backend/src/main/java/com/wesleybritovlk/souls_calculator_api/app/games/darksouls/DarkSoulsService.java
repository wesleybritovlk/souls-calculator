package com.wesleybritovlk.souls_calculator_api.app.games.darksouls;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.dto.DarkSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

public interface DarkSoulsService {
    Souls calculateSouls(DarkSoulsRequest.Souls request);
}
