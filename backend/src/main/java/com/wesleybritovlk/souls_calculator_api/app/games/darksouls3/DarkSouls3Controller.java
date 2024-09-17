package com.wesleybritovlk.souls_calculator_api.app.games.darksouls3;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto.DarkSouls3Request;

public interface DarkSouls3Controller {
    ResponseEntity<Map> postCalculateSouls(DarkSouls3Request.Souls request);
}
