package com.ks.resumeproject.error.handler;

import com.ks.resumeproject.error.domain.ErrorDto;
import com.ks.resumeproject.error.exception.CustomException;
import com.ks.resumeproject.error.exception.CustomCodeException;
import com.ks.resumeproject.error.entity.ErrorResponseEntity;
import com.ks.resumeproject.error.repository.ErrorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final ErrorMapper errorMapper;

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e) {
        return ErrorResponseEntity.errorResponseEntity(e.getErrorCode());
    }


    @ExceptionHandler(CustomCodeException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomValueException(CustomCodeException e) {

        ErrorDto errorDto = e.getErrorDto();

        if(errorDto.getMessage() == null){
            errorDto = errorMapper.getErrorByCode(e.getErrorDto().getCode());

            if(errorDto == null){
                errorDto = e.getErrorDto();
            }
        }

        return ErrorResponseEntity.errorResponseCustomEntity(errorDto);
    }
}
