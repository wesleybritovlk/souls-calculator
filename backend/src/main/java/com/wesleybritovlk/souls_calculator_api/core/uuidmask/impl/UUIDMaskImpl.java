package com.wesleybritovlk.souls_calculator_api.core.uuidmask.impl;

import java.time.Instant;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.core.uuidmask.UUIDMask;
import com.wesleybritovlk.souls_calculator_api.core.uuidmask.UUIDMaskModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UUIDMaskImpl implements UUIDMask {
    private final ConcurrentHashMap<String, UUIDMaskModel> maskMap = new ConcurrentHashMap<>();

    @Value("${app.global.ttl}")
    private long ttlSeconds;

    @Override
    public String setMaskedId(UUID originalId) {
        if (originalId == null) {
            log.error("Original id cannot be null");
            throw new NullPointerException();
        }
        log.info("Setting original ID");
        String maskedId = maskMap.entrySet().stream()
                .filter(e -> originalId.equals(e.getValue().userId()))
                .filter(e -> e.getValue().exp().isAfter(Instant.now()))
                .map(Entry::getKey)
                .findFirst()
                .orElse(UUID.randomUUID().toString());
        log.info("Generated masked ID: {}", maskedId);
        var model = UUIDMaskModel.toModel(originalId, ttlSeconds);
        maskMap.put(maskedId, model);
        log.info("Stored masked ID and model");
        return maskedId;
    }

    @Override
    public Optional<UUID> getOriginalId(String maskedId) {
        log.info("Retrieving original ID for masked ID: {}", maskedId);
        var model = maskMap.get(maskedId);
        if (model != null && model.exp().isBefore(Instant.now())) {
            log.warn("Model expired for masked ID: {}", maskedId);
            maskMap.remove(maskedId);
            return Optional.empty();
        }
        log.info("Original ID retrieved successfully for masked ID: {}", maskedId);
        return Optional.ofNullable(model).map(UUIDMaskModel::userId);
    }

    @Override
    public Optional<UUID> removeOriginalId(String maskedId) {
        log.info("Attempting to retrieve and remove original ID for masked ID: {}", maskedId);
        var model = maskMap.remove(maskedId);
        log.info("Successfully removed masked ID: {} and retrieved original ID", maskedId);
        return Optional.ofNullable(model).map(UUIDMaskModel::userId);
    }

    public long ttlMillis() {
        return ttlSeconds * 1000;
    }

    @Scheduled(fixedRateString = "#{@uuidMaskImpl.ttlMillis()}")
    public void cleanExpiredMappings() {
        log.info("Cleaning expired masks");
        maskMap.entrySet().removeIf(entry -> entry.getValue().exp().isBefore(Instant.now()));
    }
}
