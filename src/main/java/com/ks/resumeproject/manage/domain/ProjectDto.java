package com.ks.resumeproject.manage.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class ProjectDto {
    private BigInteger id;
    private String randomId;
    private String username;
    private String pageDescription;
    private String resumeYn;
    private String skillYn;
    private String actionYn;
    private String careerYn;
    private String awardYn;
    private String eduYn;
    private String coverLetterYn;
    private int portSize;
    private Date startYmd;
    private Date endYmd;

    private String copyRandomId;
    private BigInteger fileId;
    private BigInteger copyFileId;
}
