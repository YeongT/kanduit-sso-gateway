package com.kanduit.sso.exception;

import com.kanduit.sso.application.enums.ApiError;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ApiException extends Exception {
    @NonNull
    private final ApiExceptionBody body;

    private ApiException(@NonNull Exception exception, @NonNull ApiError apiError) {
        super(exception);
        this.body = new ApiExceptionBody(apiError, this);
    }

    public static ApiException of(@NonNull Exception exception, @NonNull ApiError apiError) {
        return new ApiException(exception, apiError);
    }
}