package com.wesleybritovlk.souls_calculator_api.app.games.darksouls3;

import org.springframework.http.ResponseEntity;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto.DarkSouls3Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Games - Dark Souls 3", description = "Game Dark Souls 3 operations")
public interface DarkSouls3Controller {
    ResponseEntity<MessageData<Souls>> postCalculateSouls(DarkSouls3Request.Souls request);
}
