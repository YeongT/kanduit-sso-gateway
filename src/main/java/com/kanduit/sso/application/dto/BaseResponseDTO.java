package com.kanduit.sso.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanduit.sso.application.enums.ApiStatus;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseResponseDTO {
    @NonNull
    @JsonIgnore
    private final HttpStatus httpStatus;
    @NonNull
    private final String endpoint;
    @NonNull
    private final Integer code;
    @NonNull
    private final String status;

    protected BaseResponseDTO(@NonNull String endpoint, @NonNull ApiStatus apiStatus) {
        this.endpoint = endpoint;
        this.httpStatus = apiStatus.getHttpStatus();
        this.code = apiStatus.getHttpStatus().value();
        this.status = apiStatus.name();
    }
}