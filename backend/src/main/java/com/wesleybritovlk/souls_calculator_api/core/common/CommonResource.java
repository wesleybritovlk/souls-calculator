package com.wesleybritovlk.souls_calculator_api.core.common;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResource {
    public static Map<String, Object> toMessage(String message) {
        return Map.of("message", message);
    }

    public static Map<String, Object> toMessage(String message, Object data) {
        Map<String, Object> resource = new LinkedHashMap<>();
        resource.put("message", message);
        resource.put("data", data);
        return resource;
    }

    public static Map<String, Object> toData(Object data) {
        return Map.of("data", data);
    }

    public static Map<String, Object> toPage(Page<Object> page) {
        return Map.of("data", page.getContent(),
                "current_page", page.getNumber(),
                "page_size", page.getSize(),
                "total_pages", page.getTotalPages(),
                "total_elements", page.getTotalElements(),
                "is_last", page.isLast(),
                "is_first", page.isFirst());
    }
}