package com.kanduit.sso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
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

    @NonNull
    private final String message;

    private D data;
    private E error;

    public BaseResponseDTO(@NonNull ApplicationResponseStatus status) {
        this.message = status.getMessage();
        this.status = new Status(status.getHttpStatus(), status.getCode()) {
        };
    }

    @Getter
    @AllArgsConstructor
    public abstract static class Status {
        private HttpStatus httpStatus;
        private Integer httpStatusCode;
    }
}
