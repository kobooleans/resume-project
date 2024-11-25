package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class EduDto {
    private BigInteger eduId;
    private BigInteger id;
    private String randomId;
    private String schoolCd;
    private String schoolNm;
    private String graduateState;
    private Date entranceDate;
    private Date graduateDate;
    private String major;
    private String majorAvr;
    private String schoolGb;
}