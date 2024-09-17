package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

public interface DemonSoulsService {
    Souls calculateSouls(DemonSoulsRequest.Souls request);
}
