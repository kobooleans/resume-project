package com.ks.resumeproject.util.entity;

import com.ks.resumeproject.util.domain.ErrorCode;
import com.ks.resumeproject.util.domain.ErrorDto;
import lombok.Builder;
import lombok.Data;
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
                        .name("CUSTOM_ERROR")
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }

}
