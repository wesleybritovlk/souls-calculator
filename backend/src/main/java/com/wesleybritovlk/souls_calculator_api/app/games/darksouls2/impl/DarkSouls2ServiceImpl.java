package com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.impl;

import org.springframework.stereotype.Service;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.DarkSouls2Service;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.dto.DarkSouls2Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;
import com.wesleybritovlk.souls_calculator_api.core.games.darksouls2.DarkSouls2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DarkSouls2ServiceImpl implements DarkSouls2Service {
    private final DarkSouls2 core;

    @Override
    public Souls calculateSouls(DarkSouls2Request.Souls request) {
        var calculatedSouls = core.calculateSouls(request);
        return new CommonResponse.Souls(calculatedSouls.soulsNext());
    }

}
