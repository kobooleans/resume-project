package com.ks.resumeproject.util.exception;

import com.ks.resumeproject.util.domain.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomValueException extends RuntimeException {
    ErrorDto errorDto;
}
