package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.impl;

import org.springframework.stereotype.Service;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.DemonSoulsService;
import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse;
import com.wesleybritovlk.souls_calculator_api.core.games.demonsouls.DemonSouls;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemonSoulsServiceImpl implements DemonSoulsService {
    private final DemonSouls core;

    public CommonResponse.Souls calculateSouls(DemonSoulsRequest.Souls request) {
        var calculatedSouls = core.calculateSouls(request);
        return new CommonResponse.Souls(calculatedSouls.soulsNext());
    }
}
