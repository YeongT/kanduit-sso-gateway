package com.kanduit.sso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude
public enum ApplicationResponseStatus {
    SUCCESS(HttpStatus.OK),
    FAILURE(HttpStatus.INTERNAL_SERVER_ERROR);

    @NonNull
    private final HttpStatus httpStatus;

    @NonNull
    private final Integer code;

    @NonNull
    private final String message;

    ApplicationResponseStatus(@NonNull final HttpStatus code) {
        this.httpStatus = code;
        this.code = code.value();
        this.message = this.name();
    }
}