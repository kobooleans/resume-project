package com.ks.resumeproject.error.exception;

import com.ks.resumeproject.error.domain.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    ErrorCode errorCode;
}
