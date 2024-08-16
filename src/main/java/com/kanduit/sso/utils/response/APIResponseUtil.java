package com.kanduit.sso.utils.response;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.exception.APIExceptionBody;
import com.kanduit.sso.exception.APIExceptionFactory;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public abstract class APIResponseUtil {
    public static <D, E> ResponseEntity<StandardResponseDTO<D, E>> createResponse(@NonNull StandardResponseDTO<D, E> body) {
        return new ResponseEntity<>(body, body.getStatus().httpStatus());
    }

    public static ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> createErrorResponse(@NonNull WebRequest request, @NonNull ResponseFactory responseFactory, @NonNull APIExceptionBody exceptionBody) {
        return createResponse(responseFactory.createErrorBody(request, exceptionBody.getResponseStatus(), exceptionBody));
    }

    public static ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> createSimpleErrorResponse(
            @NonNull WebRequest request,
            @NonNull ResponseFactory responseFactory,
            @NonNull APIExceptionFactory exceptionFactory,
            @NonNull APIResponseStatus responseStatus) {
        return createResponse(responseFactory.createErrorBody(request, responseStatus, exceptionFactory.createException(responseStatus).getBody()));
    }
}
