package com.kanduit.sso.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanduit.sso.dto.response.APIResponseStatus;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
public class APIExceptionBody {
    @JsonIgnore
    @NonNull
    private final APIResponseStatus responseStatus;

    @NonNull
    private final String cause;

    @NonNull
    private final List<String> comments;

    @NonNull
    private final StackTraceElement[] stackTrace;

    public APIExceptionBody(@NonNull APIResponseStatus responseStatus, @NonNull APIException exception, ArrayList<String> comments) {
        this.responseStatus = responseStatus;
        this.cause = exception.getMessage();
        this.comments = Optional.ofNullable(comments).orElseGet(ArrayList::new);
        this.stackTrace = parseStackTrace(exception.getStackTrace());
    }

    private StackTraceElement[] parseStackTrace(@NonNull StackTraceElement[] stackTrace) {
        for (int index = 0; index < stackTrace.length; index++) {
            if ("java.base".equals(stackTrace[index].getModuleName())) {
                return Arrays.copyOfRange(stackTrace, 0, index);
            }
        }
        return stackTrace;
    }
}
