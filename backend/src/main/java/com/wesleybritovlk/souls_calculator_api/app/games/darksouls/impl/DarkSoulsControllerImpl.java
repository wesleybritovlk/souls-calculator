package com.wesleybritovlk.souls_calculator_api.app.games.darksouls.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.DarkSoulsController;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.DarkSoulsService;
import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.dto.DarkSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResource;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.MessageData;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResponse.Souls;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/games/dark_souls")
@RequiredArgsConstructor
public class DarkSoulsControllerImpl implements DarkSoulsController {
    private final DarkSoulsService service;

    @PostMapping("souls")
    public ResponseEntity<MessageData<Souls>> postCalculateSouls(@RequestBody @Valid DarkSoulsRequest.Souls request) {
        var response = service.calculateSouls(request);
        var resource = CommonResource.toMessage("Souls calculated successfuly", response);
        return ResponseEntity.ok(resource);
    }
}
