package com.kanduit.sso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude
public enum APIResponseStatus {
    // example
    SUCCESS(HttpStatus.OK),
    FAILURE(HttpStatus.BAD_REQUEST),

    // server
    UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    @NonNull
    private final HttpStatus httpStatus;

    @NonNull
    private final String message;

    APIResponseStatus(@NonNull HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = this.name();
    }
}