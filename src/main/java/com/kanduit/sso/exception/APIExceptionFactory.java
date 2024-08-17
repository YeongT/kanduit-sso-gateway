package com.kanduit.sso.exception;

import com.kanduit.sso.dto.response.APIResponseStatus;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class APIExceptionFactory {

    public APIException createException(@NonNull APIResponseStatus status) {
        return new APIException(status, new RuntimeException(status.getMessage())) {
        };
    }

    public APIException convertToAPIException(@NonNull Exception exception) {
        return new APIException(APIResponseStatus.UNKNOWN_SERVER_ERROR, exception) {
        };
    }

}
