package com.kanduit.sso.exception;

import com.kanduit.sso.application.enums.ApiError;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ApiExceptionFactory {
    public ApiException createSimpleException(@NonNull ApiError status) {
        return ApiException.of(new RuntimeException(status.name()), status);
    }

    public ApiException convert(@NonNull Exception exception, @NonNull ApiError apiError) {
        return ApiException.of(exception, apiError);
    }
}