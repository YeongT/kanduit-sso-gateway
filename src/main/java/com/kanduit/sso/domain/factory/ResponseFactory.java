package com.kanduit.sso.domain.factory;

import com.kanduit.sso.dto.response.ApplicationResponseStatus;
import com.kanduit.sso.dto.response.BaseResponseDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    // Create a simple response with SUCCESS status
    public BaseResponseDTO<Void, Void> createSimpleSuccessResponse() {
        return createResponseWithStatus(ApplicationResponseStatus.SUCCESS);
    }

    // Create a simple response including data with SUCCESS status
    public <D> BaseResponseDTO<D, Void> createSimpleDataResponse(@NonNull final D data) {
        return createDataResponse(ApplicationResponseStatus.SUCCESS, data);
    }

    // Create a simple response including data with FAILURE status
    public <E> BaseResponseDTO<Void, E> createSimpleErrorResponse(@NonNull final E error) {
        return createErrorResponse(ApplicationResponseStatus.FAILURE, error);
    }

    // Create a response with a given status
    public BaseResponseDTO<Void, Void> createResponseWithStatus(@NonNull final ApplicationResponseStatus status) {
        return new BaseResponseDTO<>(status) {
        };
    }

    // Create a response with given status and data
    public <D> BaseResponseDTO<D, Void> createDataResponse(@NonNull final ApplicationResponseStatus status, @NonNull final D data) {
        return createCustomResponse(status, data, null);
    }

    // Create a response with given status and error
    public <E> BaseResponseDTO<Void, E> createErrorResponse(@NonNull final ApplicationResponseStatus status, @NonNull final E error) {
        return createCustomResponse(status, null, error);
    }

    // Create a response with custom option
    public <D, E> BaseResponseDTO<D, E> createCustomResponse(@NonNull ApplicationResponseStatus status, D data, E error) {
        return new BaseResponseDTO<D, E>(status) {
            public BaseResponseDTO<D, E> create(D data, E error) {
                this.setData(data);
                this.setError(error);
                return this;
            }
        }.create(data, error);
    }

}
