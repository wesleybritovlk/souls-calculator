package com.wesleybritovlk.souls_calculator_api.app.games.darksouls.impl;

import org.springframework.stereotype.Service;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.DarkSoulsService;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.dto.DarkSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.darksouls.DarkSouls;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DarkSoulsServiceImpl implements DarkSoulsService {
    private final DarkSouls core;

    @Override
    public Souls calculateSouls(DarkSoulsRequest.Souls request) {
        var calculatedSouls = core.calculateSouls(request);
        return new CommonResponse.Souls(calculatedSouls.soulsNext());
    }

}
