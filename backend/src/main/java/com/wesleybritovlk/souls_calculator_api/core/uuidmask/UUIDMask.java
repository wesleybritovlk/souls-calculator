package com.wesleybritovlk.souls_calculator_api.core.uuidmask;

import java.util.Optional;
import java.util.UUID;

public interface UUIDMask {
    String setMaskedId(UUID originalId);

    Optional<UUID> getOriginalId(String maskedId);

    Optional<UUID> removeOriginalId(String maskedId);
}
