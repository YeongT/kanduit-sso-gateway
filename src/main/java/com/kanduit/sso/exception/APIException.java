package com.kanduit.sso.exception;

import com.kanduit.sso.dto.response.APIResponseStatus;
import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class APIException extends Exception {
    @NonNull
    private final APIExceptionBody body;

    public APIException(@NonNull APIResponseStatus status, @NonNull Exception exception) {
        super(exception);
        this.body = new APIExceptionBody(status, this);
    }

    public APIException addComment(String comment) {
        this.body.getComments().add(comment);
        return this;
    }

}
