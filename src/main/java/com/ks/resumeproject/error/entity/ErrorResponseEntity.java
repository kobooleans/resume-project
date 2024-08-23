package com.ks.resumeproject.error.entity;

import com.ks.resumeproject.error.domain.ErrorCode;
import com.ks.resumeproject.error.domain.ErrorDto;
import com.ks.resumeproject.error.repository.ErrorMapper;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponseEntity {

    private int status;
    private String message;
    private String name;
    private String code;

    public static ResponseEntity<ErrorResponseEntity> errorResponseEntity(ErrorCode e) {
     return ResponseEntity
             .status(e.getHttpStatus())
             .body(ErrorResponseEntity.builder()
                     .status(e.getHttpStatus().value())
                     .name(e.name())
                     .code(e.getCode())
                     .message(e.getMessage())
                     .build());
    }

    public static ResponseEntity<ErrorResponseEntity> errorResponseCustomEntity(ErrorDto e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseEntity.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .name(e.getName() == null ? "" : e.getName())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }

}
