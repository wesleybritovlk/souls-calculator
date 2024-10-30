package com.wesleybritovlk.souls_calculator_api.app.games.darksouls2;

import org.springframework.http.ResponseEntity;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.dto.DarkSouls2Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Games - Dark Souls 2", description = "Game Dark Souls 2 operations")
public interface DarkSouls2Controller {
    ResponseEntity<MessageData<Souls>> postCalculateSouls(DarkSouls2Request.Souls request);
}
