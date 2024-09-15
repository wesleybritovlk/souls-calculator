package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest;

public interface DemonSoulsController {
    ResponseEntity<Map> postCalculateSouls(DemonSoulsRequest.Souls request);
}
