package com.ks.resumeproject.users.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AccountMyPageDto {

    private BigInteger id;
    private String randomId;
    private BigInteger pageId;
    private String pageDescription;
}
