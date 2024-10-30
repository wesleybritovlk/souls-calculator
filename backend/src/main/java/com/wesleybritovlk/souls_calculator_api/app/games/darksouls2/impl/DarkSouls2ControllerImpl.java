package com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.DarkSouls2Controller;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.DarkSouls2Service;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls2.dto.DarkSouls2Request;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResource;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/games/dark_souls_2")
@RequiredArgsConstructor
public class DarkSouls2ControllerImpl implements DarkSouls2Controller {
    private final DarkSouls2Service service;

    @PostMapping("souls")
    public ResponseEntity<MessageData<Souls>> postCalculateSouls(@RequestBody @Valid DarkSouls2Request.Souls request) {
        var response = service.calculateSouls(request);
        var resource = CommonResource.toMessage("Souls calculated successfuly", response);
        return ResponseEntity.ok(resource);
    }
}
