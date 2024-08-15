package com.kanduit.sso.exception;

import com.kanduit.sso.dto.response.APIResponseStatus;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;

@Getter
public abstract class APIException extends Exception {
    @NonNull
    private final APIExceptionBody body;

    public APIException(@NonNull APIResponseStatus status, @NonNull Exception exception, ArrayList<String> comments) {
        super(exception);
        this.body = new APIExceptionBody(status, this, comments);
    }

    public APIException addComment(String comment) {
        this.body.getComments().add(comment);
        return this;
    }

}
