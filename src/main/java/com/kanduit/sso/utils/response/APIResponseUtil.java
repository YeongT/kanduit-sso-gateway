package com.kanduit.sso.utils.response;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.exception.APIExceptionBody;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class APIResponseUtil {
    @NonNull
    private final ResponseFactory responseFactory;

    public APIResponseUtil(@NonNull ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public <D, E> ResponseEntity<StandardResponseDTO<D, E>> createResponse(@NonNull StandardResponseDTO<D, E> body) {
        return new ResponseEntity<>(body, body.getStatus().httpStatus());
    }

    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> createErrorResponse(@NonNull WebRequest request, @NonNull APIExceptionBody exceptionBody) {
        return createResponse(responseFactory.createErrorBody(request, exceptionBody.getResponseStatus(), exceptionBody));
    }
}

