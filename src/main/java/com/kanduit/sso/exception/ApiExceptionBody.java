package com.kanduit.sso.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kanduit.sso.application.enums.ApiError;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@JsonPropertyOrder({"cause"})
public class ApiExceptionBody {
    @JsonIgnore
    @NonNull
    private final ApiError apiError;
    @NonNull
    private final String cause;
    @NonNull
    private final String message;
    @NonNull
    private final ArrayList<String> comments;
    @NonNull
    private final StackTraceElement[] stackTrace;

    public ApiExceptionBody(@NonNull ApiError apiError, @NonNull ApiException apiException) {
        this.apiError = apiError;
        this.cause = apiException.getMessage();
        this.message = apiError.getMessage();
        this.comments = new ArrayList<>();
        this.stackTrace = parseStackTrace(apiException.getCause().getStackTrace());
    }

    private StackTraceElement[] parseStackTrace(@NonNull StackTraceElement[] stackTrace) {
        //TODO: improve logging and exception handling
        StackTraceElement[] filteredStackTraceArray = Arrays.stream(stackTrace)
                .filter(element ->
                        element.getClassName().startsWith("com.kanduit.sso") && element.getLineNumber() > 0)
                .toArray(StackTraceElement[]::new);
        return filteredStackTraceArray.length > 0 ? filteredStackTraceArray : Arrays.copyOfRange(stackTrace, 0, Math.min(5, stackTrace.length));
    }

    public void addComment(@NonNull String comment) {
        this.comments.add(comment);
    }

    public ArrayList<String> getComments() {
        return new ArrayList<>(comments);
    }
}