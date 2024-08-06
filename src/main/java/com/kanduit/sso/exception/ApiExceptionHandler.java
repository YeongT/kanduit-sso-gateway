package com.kanduit.sso.exception;

import com.kanduit.sso.application.dto.ErrorResponseDTO;
import com.kanduit.sso.application.enums.ApiError;
import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.utils.ResponseUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ControllerAdvice
public class ApiExceptionHandler {
    private final ResponseUtil responseUtil;
    private final ResponseFactory responseFactory;
    private final ApiExceptionFactory apiExceptionFactory;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleApiException(WebRequest request, ApiException exception) {
        return responseUtil.wrap(responseFactory.createErrorResponse(request, exception.getBody()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(WebRequest request, ConstraintViolationException exception) {
        ApiExceptionBody exceptionBody = apiExceptionFactory.createSimpleException(ApiError.FIELD_VALIDATION_FAILED).getBody();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            exceptionBody.addComment("Field '%s.%s' failed validation: %s".formatted(violation.getRootBeanClass().getSimpleName(), violation.getPropertyPath(), violation.getMessage()));
        }
        return responseUtil.wrap(responseFactory.createErrorResponse(request, exceptionBody));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalExceptions(WebRequest request, Exception exception) {
        return responseUtil.wrap(responseFactory.createErrorResponse(request, apiExceptionFactory.convert(exception, ApiError.UNKNOWN).getBody()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(WebRequest request, HttpMessageNotReadableException exception) {
        ApiExceptionBody exceptionBody = apiExceptionFactory.createSimpleException(ApiError.INVALID_REQUEST_BODY).getBody();
        exceptionBody.addComment("Failed to parse request body: %s".formatted(exception.getMessage()));
        return responseUtil.wrap(responseFactory.createErrorResponse(request, exceptionBody));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(WebRequest request, MethodArgumentNotValidException validationException) {
        ApiExceptionBody exceptionBody = apiExceptionFactory.createSimpleException(ApiError.METHOD_ARGUMENT_NOT_VALID).getBody();
        for (FieldError fieldError : validationException.getFieldErrors()) {
            exceptionBody.addComment("Field '%s' on object '%s' failed validation '%s'. Rejected value: '%s'".formatted(fieldError.getField(), fieldError.getObjectName(), Arrays.toString(fieldError.getCodes()), fieldError.getRejectedValue()));
        }
        return responseUtil.wrap(responseFactory.createErrorResponse(request, exceptionBody));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingServletRequestParameterException(WebRequest request, MissingServletRequestParameterException exception) {
        ApiExceptionBody exceptionBody = apiExceptionFactory.createSimpleException(ApiError.MISSING_REQUIRED_PARAMETER).getBody();
        exceptionBody.addComment("Required parameter '%s' not found".formatted(exception.getParameterName()));
        return responseUtil.wrap(responseFactory.createErrorResponse(request, exceptionBody));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFoundException(WebRequest request, NoResourceFoundException exception) {
        return responseUtil.wrap(responseFactory.createErrorResponse(request, apiExceptionFactory.convert(exception, ApiError.NO_RESOURCE).getBody()));
    }
}