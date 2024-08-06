package com.kanduit.sso.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public enum ApiError {
    // common
    FIELD_VALIDATION_FAILED(ApiStatus.BAD_REQUEST, "field validation failed."),
    INVALID_REQUEST_BODY(ApiStatus.BAD_REQUEST, "Invalid request body."),
    METHOD_ARGUMENT_NOT_VALID(ApiStatus.BAD_REQUEST, "Invalid method argument."),
    MISSING_REQUIRED_PARAMETER(ApiStatus.BAD_REQUEST, "Missing required parameter."),
    NO_RESOURCE(ApiStatus.NOT_FOUND, "Requested resource not found."),
    UNKNOWN(ApiStatus.UNKNOWN_SERVER_ERROR, "Sorry, an unexpected issue has occurred. Please get in touch with the administrator.");

    @NonNull
    private final ApiStatus apiStatus;
    @NonNull
    private final String message;
}