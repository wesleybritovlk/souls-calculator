package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsResponse;

public interface DemonSoulsService {
    DemonSoulsResponse.Souls calculateSouls(DemonSoulsRequest.Souls request);
}
