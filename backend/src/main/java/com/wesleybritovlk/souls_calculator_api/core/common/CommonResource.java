package com.wesleybritovlk.souls_calculator_api.core.common;

import org.springframework.data.domain.Page;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResource {
    public static CommonResponse.Message toMessage(String message) {
        return new CommonResponse.Message(message);
    }

    public static <T> CommonResponse.MessageData<T> toMessage(String message, T data) {
        return new CommonResponse.MessageData<T>(message, data);
    }

    public static <T> CommonResponse.Data<T> toData(T data) {
        return new CommonResponse.Data<T>(data);
    }

    public static <T> CommonResponse.Page<T> toPage(Page<T> page) {
        return new CommonResponse.Page<T>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty());
    }
}