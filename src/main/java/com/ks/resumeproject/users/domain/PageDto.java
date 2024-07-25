package com.ks.resumeproject.users.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PageDto {
    private BigInteger id;
    private String username;
    private String randomId;
    private BigInteger accountId;
    private BigInteger pageId;
    private String pageDescription;
    private String useYn;
}
