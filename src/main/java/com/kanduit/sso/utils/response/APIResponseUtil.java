package com.kanduit.sso.utils.response;

import com.kanduit.sso.dto.response.StandardResponseDTO;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class APIResponseUtil {
    public <D, E> ResponseEntity<StandardResponseDTO<D, E>> createResponse(@NonNull StandardResponseDTO<D, E> body) {
        return new ResponseEntity<>(body, body.getStatus().httpStatus());
    }
}

