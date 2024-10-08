package com.kanduit.sso.domain.factory;

import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.dto.response.StandardResponseDTO;
import com.kanduit.sso.exception.APIExceptionBody;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class ResponseFactory {
    // Create a simple response body with given status
    public StandardResponseDTO<Void, Void> createSimpleStatusBody(@NonNull WebRequest request, @NonNull APIResponseStatus status) {
        return createCustomBody(request, status, null, null);
    }

    // Create a response body with given status and data
    public <D> StandardResponseDTO<D, Void> createDataBody(@NonNull WebRequest request, @NonNull APIResponseStatus status, @NonNull D data) {
        return createCustomBody(request, status, data, null);
    }

    // Create a response body with given status and error
    public StandardResponseDTO<Void, APIExceptionBody> createErrorBody(@NonNull WebRequest request, @NonNull APIResponseStatus status, @NonNull APIExceptionBody errorBody) {
        return createCustomBody(request, status, null, errorBody);
    }

    // Create a response body with custom option
    private <D, E> StandardResponseDTO<D, E> createCustomBody(@NonNull WebRequest request, @NonNull APIResponseStatus status, D data, E error) {
        return new StandardResponseDTO<D, E>(request, status) {
            public StandardResponseDTO<D, E> create(D data, E error) {
                this.setData(data);
                this.setError(error);
                return this;
            }
        }.create(data, error);
    }
}
