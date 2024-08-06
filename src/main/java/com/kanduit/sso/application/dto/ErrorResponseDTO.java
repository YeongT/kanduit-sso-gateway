package com.kanduit.sso.application.dto;

import com.kanduit.sso.application.enums.ApiStatus;
import com.kanduit.sso.exception.ApiExceptionBody;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ErrorResponseDTO extends BaseResponseDTO {
    @NonNull
    private final ApiExceptionBody error;

    public ErrorResponseDTO(@NonNull String endpoint, @NonNull ApiStatus apiStatus, @NonNull ApiExceptionBody apiExceptionBody) {
        super(endpoint, apiStatus);
        this.error = apiExceptionBody;
    }

    public static ErrorResponseDTO of(@NonNull String endpoint, @NonNull ApiStatus apiStatus, @NonNull ApiExceptionBody apiExceptionBody) {
        return new ErrorResponseDTO(endpoint, apiStatus, apiExceptionBody);
    }
}