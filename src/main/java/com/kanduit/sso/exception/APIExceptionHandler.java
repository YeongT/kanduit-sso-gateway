package com.kanduit.sso.exception;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.utils.response.APIResponseUtil;
import com.nbp.ncp.nes.exception.ApiException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;

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

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleApiException(WebRequest request, ApiException exception) {
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionFactory.convertToAPIException(exception, APIResponseStatus.MAIL_SEND_FAILED).getBody());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleNoResourceFoundException(WebRequest request, NoResourceFoundException exception) {
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionFactory.convertToAPIException(exception, APIResponseStatus.NOT_FOUND).getBody());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleConstraintViolationException(WebRequest request, ConstraintViolationException exception) {
        APIExceptionBody exceptionBody = exceptionFactory.createException(APIResponseStatus.FIELD_VALIDATION_FAILED).getBody();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            exceptionBody.addComment("Field '%s.%s' failed validation: %s".formatted(violation.getRootBeanClass().getSimpleName(), violation.getPropertyPath(), violation.getMessage()));
        }
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleValidationException(WebRequest request, MethodArgumentNotValidException validationException) {
        APIExceptionBody exceptionBody = exceptionFactory.createException(APIResponseStatus.FIELD_VALIDATION_FAILED).getBody();
        for (FieldError fieldError : validationException.getFieldErrors()) {
            exceptionBody.addComment(
                    "Field '%s' on object '%s' failed validation '%s'. Rejected value: '%s'"
                            .formatted(
                                    fieldError.getField(),
                                    fieldError.getObjectName(),
                                    Arrays.toString(fieldError.getCodes()),
                                    fieldError.getRejectedValue()
                            )
            );
        }
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleGlobalExceptions(WebRequest request, Exception exception) {
        return APIResponseUtil.createErrorResponse(request, responseFactory, exceptionFactory.convertToAPIException(exception).getBody());
    }
}
