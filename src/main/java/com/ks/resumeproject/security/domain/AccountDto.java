package com.ks.resumeproject.security.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountDto {
    private BigInteger id;
    private String username;
    private String password;
    private String roleType;
    private String randomId;
}
