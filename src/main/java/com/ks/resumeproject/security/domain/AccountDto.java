package com.ks.resumeproject.security.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
public class AccountDto {
    private BigInteger id;
    private String username;
    private String userEmail;
    private String authCode;
    private String password;
    private String roleType;
    private String randomId;
    private BigInteger fileId;


    public AccountDto(){

    }

    public AccountDto(BigInteger id, String username, String password, String roleType, String randomId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleType = roleType;
        this.randomId = randomId;
    }

    public AccountDto(BigInteger id, String username, String password, String userEmail, String authCode, String roleType, String randomId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
        this.authCode = authCode;
        this.roleType = roleType;
        this.randomId = randomId;
    }
}
