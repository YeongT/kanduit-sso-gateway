package com.kanduit.sso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude
public enum ApplicationResponseStatus {
    SUCCESS(HttpStatus.OK),
    FAILURE(HttpStatus.INTERNAL_SERVER_ERROR),

    // user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT),
    USER_INITIALIZATION_SUCCESS(HttpStatus.OK),
    USER_INITIALIZATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),

    // email
    EMAIL_VERIFICATION_SUCCESS(HttpStatus.OK),
    EMAIL_VERIFICATION_MAIL_EXPIRED(HttpStatus.BAD_REQUEST),
    EMAIL_VERIFICATION_MAIL_SEND_SUCCESS(HttpStatus.OK),
    EMAIL_VERIFICATION_MAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR),

    // validation
    EMAIL_FORMAT_ERROR(HttpStatus.BAD_REQUEST),

    // server
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR);


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