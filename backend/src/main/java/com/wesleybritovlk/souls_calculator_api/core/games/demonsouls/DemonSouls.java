package com.wesleybritovlk.souls_calculator_api.core.games.demonsouls;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.model.DemonSoulsModel.SoulsNext;

public interface DemonSouls {
    SoulsNext calculateSouls(Souls request);
}
