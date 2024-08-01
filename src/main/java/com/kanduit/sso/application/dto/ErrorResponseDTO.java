package com.kanduit.sso.application.dto;

import com.kanduit.sso.application.enums.ApiStatus;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ErrorResponseDTO<E>  extends BaseResponseDTO {
    @NonNull
    // TODO: Specify the type of error details or create a dedicated error class to encapsulate error information
    private final E error;

    private ErrorResponseDTO(@NonNull String endpoint, @NonNull ApiStatus apiStatus, @NonNull E error) {
        super(endpoint, apiStatus);
        this.error = error;
    }

    public static <T> ErrorResponseDTO<T> of(@NonNull String endpoint, @NonNull ApiStatus status, @NonNull T data) {
        return new ErrorResponseDTO<>(endpoint, status, data);
    }
}