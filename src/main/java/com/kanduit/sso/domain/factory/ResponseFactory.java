package com.kanduit.sso.domain.factory;

import com.kanduit.sso.application.dto.BaseResponseDTO;
import com.kanduit.sso.application.dto.DataResponseDTO;
import com.kanduit.sso.application.dto.ErrorResponseDTO;
import com.kanduit.sso.application.enums.ApiStatus;
import com.kanduit.sso.exception.ApiExceptionBody;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Component
public class ResponseFactory {
    public <D> DataResponseDTO<D> createDataResponse(@NonNull WebRequest request, @NonNull ApiStatus apiStatus, @NonNull D data) {
        return DataResponseDTO.of(extractEndpoint(request), apiStatus, data);
    }

    public ErrorResponseDTO createErrorResponse(@NonNull WebRequest request, @NonNull ApiExceptionBody apiExceptionBody) {
        return ErrorResponseDTO.of(extractEndpoint(request), apiExceptionBody.getApiError().getApiStatus(), apiExceptionBody);
    }

    public BaseResponseDTO createSimpleResponse(
            @NonNull WebRequest request,
            @NonNull ApiStatus status) {
        return new BaseResponseDTO(extractEndpoint(request), status) {
        };
    }

    private String extractEndpoint(@NonNull WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}