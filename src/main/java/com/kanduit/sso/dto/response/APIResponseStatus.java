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

    // mail
    MAIL_SEND_SUCCESS(HttpStatus.OK),
    MAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),
    MAIL_TEMPLATE_CREATE_FAILED(HttpStatus.BAD_REQUEST),
    
    // validation
    FIELD_VALIDATION_FAILED(HttpStatus.BAD_REQUEST),

    // server
    ACCESS_DENIED(HttpStatus.FORBIDDEN),
    TIMEOUT(HttpStatus.GATEWAY_TIMEOUT),
    NOT_FOUND(HttpStatus.NOT_FOUND),
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