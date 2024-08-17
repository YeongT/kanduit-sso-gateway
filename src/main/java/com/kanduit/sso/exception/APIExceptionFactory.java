package com.kanduit.sso.exception;

import com.kanduit.sso.dto.response.APIResponseStatus;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class APIExceptionFactory {

    public APIException createException(@NonNull APIResponseStatus status, ArrayList<String> comments) {
        return new APIException(status, new RuntimeException(status.getMessage()), comments) {
        };
    }

    public APIException convertToAPIException(@NonNull Exception exception, ArrayList<String> comments) {
        return new APIException(APIResponseStatus.UNKNOWN_SERVER_ERROR, exception, comments) {
        };
    }

}
