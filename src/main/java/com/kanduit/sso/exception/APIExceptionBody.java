package com.kanduit.sso.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanduit.sso.dto.response.APIResponseStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class APIExceptionBody {
    @JsonIgnore
    @NonNull
    private final APIResponseStatus responseStatus;
    @NonNull
    private final ArrayList<String> comments;
    @NonNull
    private final StackTraceElement[] stackTrace;
    @NonNull
    @Setter
    private String cause;

    public APIExceptionBody(@NonNull APIResponseStatus responseStatus, @NonNull APIException exception) {
        this.responseStatus = responseStatus;
        this.cause = exception.getMessage();
        this.comments = new ArrayList<>();
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

    public APIExceptionBody addComment(@NonNull String comment) {
        this.comments.add(comment);
        return this;
    }

    public ArrayList<String> getComments() {
        return new ArrayList<>(comments);
    }
}
