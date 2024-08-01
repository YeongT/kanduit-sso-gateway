package com.kanduit.sso.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiStatus {
    // example
    SUCCESS(HttpStatus.OK),

    // server
    UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    @NonNull
    private final HttpStatus httpStatus;
}