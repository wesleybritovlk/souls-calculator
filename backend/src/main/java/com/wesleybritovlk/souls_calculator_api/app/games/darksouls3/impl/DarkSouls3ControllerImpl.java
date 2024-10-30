package com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.DarkSouls3Controller;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.DarkSouls3Service;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls3.dto.DarkSouls3Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResource;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/games/dark_souls_3")
@RequiredArgsConstructor
public class DarkSouls3ControllerImpl implements DarkSouls3Controller {
    private final DarkSouls3Service service;

    @PostMapping("souls")
    public ResponseEntity<MessageData<Souls>> postCalculateSouls(@RequestBody @Valid DarkSouls3Request.Souls request) {
        var response = service.calculateSouls(request);
        var resource = CommonResource.toMessage("Souls calculated successfuly", response);
        return ResponseEntity.ok(resource);
    }
}
