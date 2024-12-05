package com.ks.resumeproject.security.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class EmailCodeDto {
    private BigInteger id;
    private String userEmail;
    private String authCode;
    private String codeSendDate;

    public EmailCodeDto(){

    }

    public EmailCodeDto(String userEmail, String authCode) {
        this.userEmail = userEmail;
        this.authCode = authCode;
    }
}
