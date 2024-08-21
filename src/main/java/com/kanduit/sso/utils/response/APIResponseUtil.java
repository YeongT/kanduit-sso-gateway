package com.kanduit.sso.utils.response;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.exception.APIExceptionBody;
import com.kanduit.sso.exception.APIExceptionFactory;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class APIResponseUtil {
    @NonNull
    private final ResponseFactory responseFactory;

    @NonNull
    private final APIExceptionFactory exceptionFactory;

    public APIResponseUtil(@NonNull ResponseFactory responseFactory, @NonNull APIExceptionFactory exceptionFactory) {
        this.responseFactory = responseFactory;
        this.exceptionFactory = exceptionFactory;
    }

    public <D, E> ResponseEntity<StandardResponseDTO<D, E>> createResponse(@NonNull StandardResponseDTO<D, E> body) {
        return new ResponseEntity<>(body, body.getStatus().httpStatus());
    }

    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> createErrorResponse(@NonNull WebRequest request, @NonNull APIExceptionBody exceptionBody) {
        return createResponse(responseFactory.createErrorBody(request, exceptionBody.getResponseStatus(), exceptionBody));
    }

    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> createSimpleErrorResponse(
            @NonNull WebRequest request,
            @NonNull APIResponseStatus responseStatus) {
        return createResponse(responseFactory.createErrorBody(request, responseStatus, exceptionFactory.createException(responseStatus).getBody()));
    }
}

