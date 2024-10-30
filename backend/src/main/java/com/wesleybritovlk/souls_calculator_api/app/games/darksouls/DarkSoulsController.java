package com.wesleybritovlk.souls_calculator_api.app.games.darksouls;

import org.springframework.http.ResponseEntity;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.dto.DarkSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Games - Dark Souls", description = "Game Dark Souls operations")
public interface DarkSoulsController {
    ResponseEntity<MessageData<Souls>> postCalculateSouls(DarkSoulsRequest.Souls request);
}
