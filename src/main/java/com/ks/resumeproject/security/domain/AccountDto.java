package com.ks.resumeproject.security.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
public class AccountDto {
    private BigInteger id;
    private String username;
    private String password;
    private String roleType;
    private String randomId;

    public AccountDto(BigInteger id, String username, String password, String roleType, String randomId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleType = roleType;
        this.randomId = randomId;
    }
}
