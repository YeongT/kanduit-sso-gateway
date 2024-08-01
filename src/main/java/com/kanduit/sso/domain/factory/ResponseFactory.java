package com.kanduit.sso.domain.factory;

import com.kanduit.sso.application.dto.BaseResponseDTO;
import com.kanduit.sso.application.dto.DataResponseDTO;
import com.kanduit.sso.application.dto.ErrorResponseDTO;
import com.kanduit.sso.application.enums.ApiStatus;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Component
public class ResponseFactory {
    public <D> DataResponseDTO<D> createDataResponse(@NonNull WebRequest request, @NonNull ApiStatus apiStatus, @NonNull D data) {
        return DataResponseDTO.of(extractEndpoint(request), apiStatus, data);
    }

    public <E> ErrorResponseDTO<E> createErrorResponse(@NonNull WebRequest request, @NonNull ApiStatus apiStatus, @NonNull E error) {
        return ErrorResponseDTO.of(extractEndpoint(request), apiStatus, error);
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