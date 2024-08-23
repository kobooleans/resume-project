package com.ks.resumeproject.error.exception;

import com.ks.resumeproject.error.domain.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomCodeException extends RuntimeException {
    ErrorDto errorDto;
}
