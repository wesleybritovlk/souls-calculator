package com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.impl;

import org.springframework.stereotype.Service;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.DarkSouls3Service;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto.DarkSouls3Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.darksouls3.DarkSouls3;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DarkSouls3ServiceImpl implements DarkSouls3Service {
    private final DarkSouls3 core;

    @Override
    public Souls calculateSouls(DarkSouls3Request.Souls request) {
        var calculatedSouls = core.calculateSouls(request);
        return new CommonResponse.Souls(calculatedSouls.soulsNext());
    }

}
