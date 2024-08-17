package com.kanduit.sso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Getter
@Setter
@JsonInclude
public abstract class StandardResponseDTO<D, E> {
    @NonNull
    private final String endpoint;

    @NonNull
    private final Status status;

    private D data;
    private E error;

    public StandardResponseDTO(@NonNull WebRequest request, @NonNull APIResponseStatus responseStatus) {
        this.endpoint = ((ServletWebRequest) request).getRequest().getRequestURI();
        this.status = new Status(responseStatus.getHttpStatus(), responseStatus.getHttpStatus().value(), responseStatus.getMessage());
    }

    public record Status(@NonNull HttpStatus httpStatus, @NonNull Integer httpStatusCode, @NonNull String message) {
    }
}
