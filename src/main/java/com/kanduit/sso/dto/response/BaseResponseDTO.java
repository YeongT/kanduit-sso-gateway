package com.kanduit.sso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude
public abstract class BaseResponseDTO<D, E> {
    @NonNull
    private final Status status;

    private D data;
    private E error;

    public BaseResponseDTO(@NonNull APIResponseStatus responseStatus) {
        this.status = new Status(responseStatus.getHttpStatus(), responseStatus.getHttpStatus().value(), responseStatus.getMessage());
    }

    public record Status(@NonNull HttpStatus httpStatus, @NonNull Integer httpStatusCode, @NonNull String message) {
    }
}
