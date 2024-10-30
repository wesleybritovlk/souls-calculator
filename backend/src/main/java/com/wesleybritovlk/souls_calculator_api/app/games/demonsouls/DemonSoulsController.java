package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls;

import org.springframework.http.ResponseEntity;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Games - Demon Souls", description = "Game Demon Souls operations")
public interface DemonSoulsController {
    ResponseEntity<MessageData<Souls>> postCalculateSouls(DemonSoulsRequest.Souls request);
}
