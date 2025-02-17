package com.ks.resumeproject.error.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNKNOWN_EXCEPTION(HttpStatus.BAD_REQUEST, "UNKNOWN-EXCEPTION", "알수없는 오류가 발생하였습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH-F01", "정보가 만료되어 로그아웃 되었습니다. 로그인되지 않은 사용자이므로, 접근을 할 수 없습니다."),
    NOT_FOUND_VIEWER(HttpStatus.NOT_FOUND, "NT-F01", "해당 정보는 정의되어 있지 않습니다. 접근이 거부되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
