package com.ks.resumeproject.util.handler;

import com.ks.resumeproject.util.entity.ErrorResponseEntity;
import com.ks.resumeproject.util.exception.CustomException;
import com.ks.resumeproject.util.exception.CustomValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e) {
        return ErrorResponseEntity.errorResponseEntity(e.getErrorCode());
    }


    @ExceptionHandler(CustomValueException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomValueException(CustomValueException e) {
        return ErrorResponseEntity.errorResponseCustomEntity(e.getErrorDto());
    }
}
