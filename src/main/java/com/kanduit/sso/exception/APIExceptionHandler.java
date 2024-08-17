package com.kanduit.sso.exception;

import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.utils.response.APIResponseUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
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
    @NonNull
    private final APIResponseUtil apiResponseUtil;

    @NonNull
    private final APIExceptionFactory exceptionFactory;

    @Autowired
    public APIExceptionHandler(@NonNull APIResponseUtil apiResponseUtil, @NonNull APIExceptionFactory exceptionFactory) {
        this.apiResponseUtil = apiResponseUtil;
        this.exceptionFactory = exceptionFactory;
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleCustomException(WebRequest request, APIException exception) {
        return apiResponseUtil.createErrorResponse(request, exception.getBody());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleNoResourceFoundException(WebRequest request, NoResourceFoundException exception) {
        return apiResponseUtil.createErrorResponse(request, exceptionFactory.convertToAPIException(exception, APIResponseStatus.NOT_FOUND).getBody());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleConstraintViolationException(WebRequest request, ConstraintViolationException exception) {
        APIExceptionBody exceptionBody = exceptionFactory.createException(APIResponseStatus.FIELD_VALIDATION_FAILED).getBody();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            exceptionBody.addComment("Field '%s.%s' failed validation: %s".formatted(violation.getRootBeanClass().getSimpleName(), violation.getPropertyPath(), violation.getMessage()));
        }
        return apiResponseUtil.createErrorResponse(request, exceptionBody);
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
        return apiResponseUtil.createErrorResponse(request, exceptionBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponseDTO<Void, APIExceptionBody>> handleGlobalExceptions(WebRequest request, Exception exception) {
        return apiResponseUtil.createErrorResponse(request, exceptionFactory.convertToAPIException(exception).getBody());
    }
}
