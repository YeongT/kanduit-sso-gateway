package com.kanduit.sso.utils.response;

import com.kanduit.sso.dto.response.BaseResponseDTO;
import org.springframework.http.ResponseEntity;

public abstract class APIResponseUtil {
    public static <D, E> ResponseEntity<BaseResponseDTO<?, ?>> createResponse(BaseResponseDTO<D, E> body) {
        return new ResponseEntity<>(body, body.getStatus().getHttpStatus());
    }
}

