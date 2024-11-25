package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AwardDto {
    private String awardKey;
    private String initAwardKey;
    private BigInteger id;
    private String randomId;
    private BigInteger seq;

    //자격
    private BigInteger licenseId;
    private String licenseNm;
    private String licenseIssueOrg;
    private String licensePassYn;
    private String licensePassDate;

    //어학
    private BigInteger langTestId;
    private String langNm;
    private String langTestNm;
    private String langAcquireDate;
    private String langTestScore;
    private String langAcquireYn;

    //수상
    private BigInteger awardId;
    private String awardNm;
    private String awardDate;
    private String awardOrg;
}
