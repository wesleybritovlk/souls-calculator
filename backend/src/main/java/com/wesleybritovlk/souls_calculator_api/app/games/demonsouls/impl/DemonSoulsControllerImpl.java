package com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.impl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.DemonSoulsController;
import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.DemonSoulsService;
import com.wesleybritovlk.souls_calculator_api.app.games.demonsouls.dto.DemonSoulsRequest;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonResource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/games/demon_souls")
@RequiredArgsConstructor
public class DemonSoulsControllerImpl implements DemonSoulsController {
    private final DemonSoulsService service;

    @PostMapping("souls")
    public ResponseEntity<Map> postCalculateSouls(@RequestBody @Valid DemonSoulsRequest.Souls request) {
        var response = service.calculateSouls(request);
        var resource = CommonResource.toMessage("Souls calculated successfuly", response);
        return ResponseEntity.ok(resource);
    }

}
