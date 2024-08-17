package com.kanduit.sso.exception;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.utils.response.APIResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class APIExceptionHandler {
    private final APIExceptionFactory exceptionFactory;
    private final ResponseFactory responseFactory;

    @Autowired
    public APIExceptionHandler(APIExceptionFactory exceptionFactory, ResponseFactory responseFactory) {
        this.exceptionFactory = exceptionFactory;
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleCustomException(WebRequest request, APIException exception) {
        return APIResponseUtil.createErrorResponse(request, responseFactory, exception.getBody());
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleNoResourceFoundException(WebRequest request, NoResourceFoundException exception) {
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionFactory.convertToAPIException(exception, APIResponseStatus.NOT_FOUND).getBody());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleGlobalExceptions(WebRequest request, Exception exception) {
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionFactory.convertToAPIException(exception).getBody());
    }
}
