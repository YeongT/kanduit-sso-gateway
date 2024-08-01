package com.kanduit.sso.utils;

import com.kanduit.sso.application.dto.BaseResponseDTO;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    public <T> ResponseEntity<?> create(@NonNull T body, @NonNull HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }

    public <T extends BaseResponseDTO> ResponseEntity<T> wrap(@NonNull T responseDTO) {
        return new ResponseEntity<>(responseDTO, responseDTO.getHttpStatus());
    }
}