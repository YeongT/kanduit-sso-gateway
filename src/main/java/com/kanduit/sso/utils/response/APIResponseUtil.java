package com.kanduit.sso.utils.response;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public abstract class APIResponseUtil {
    public static <D, E> ResponseEntity<StandardResponseDTO<D, E>> createResponse(@NonNull StandardResponseDTO<D, E> body) {
        return new ResponseEntity<>(body, body.getStatus().httpStatus());
    }

    public static <E> ResponseEntity<StandardResponseDTO<Void, E>> createErrorResponse(@NonNull WebRequest request, @NonNull APIResponseStatus status, @NonNull ResponseFactory responseFactory, @NonNull E error) {
        return createResponse(responseFactory.createErrorBody(request, status, error));
    }
}
