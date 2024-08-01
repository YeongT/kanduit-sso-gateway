package com.kanduit.sso.domain.factory;

import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.BaseResponseDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {
    // Create a simple response body with given status
    public BaseResponseDTO<Void, Void> createSimpleStatusBody(@NonNull APIResponseStatus status) {
        return createCustomBody(status, null, null);
    }

    // Create a response body with given status and data
    public <D> BaseResponseDTO<D, Void> createDataBody(@NonNull APIResponseStatus status, @NonNull D data) {
        return createCustomBody(status, data, null);
    }

    // Create a response body with given status and error
    public <E> BaseResponseDTO<Void, E> createErrorBody(@NonNull APIResponseStatus status, @NonNull E error) {
        return createCustomBody(status, null, error);
    }

    // Create a response body with custom option
    public <D, E> BaseResponseDTO<D, E> createCustomBody(@NonNull APIResponseStatus status, D data, E error) {
        return new BaseResponseDTO<D, E>(status) {
            public BaseResponseDTO<D, E> create(D data, E error) {
                this.setData(data);
                this.setError(error);
                return this;
            }
        }.create(data, error);
    }

}
