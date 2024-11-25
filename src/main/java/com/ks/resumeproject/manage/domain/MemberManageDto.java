package com.ks.resumeproject.manage.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class MemberManageDto {
    private BigInteger id;
    private String username;
    private String currentPw;
    private String newPw;
    private String newPwChk;
    private String randomId;
}
