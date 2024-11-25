package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ResumeDto {

    private BigInteger id;
    private String randomId;
    private boolean skillYn;
    private boolean careerYn;
    private boolean eduYn;
    private boolean awardYn;
    private boolean actionYn;
    private boolean coverLetterYn;
    private boolean skillImgYn;
    private String type;
    private String isYn;
}
