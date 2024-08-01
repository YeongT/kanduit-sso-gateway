package com.kanduit.sso.application.dto;

import com.kanduit.sso.application.enums.ApiStatus;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class DataResponseDTO<D> extends BaseResponseDTO {
    @NonNull
    private final D data;

    private DataResponseDTO(@NonNull String endpoint, @NonNull ApiStatus apiStatus, @NonNull D data) {
        super(endpoint, apiStatus);
        this.data = data;
    }

    public static <T> DataResponseDTO<T> of(@NonNull String endpoint, @NonNull ApiStatus status, @NonNull T data) {
        return new DataResponseDTO<>(endpoint, status, data);
    }
}