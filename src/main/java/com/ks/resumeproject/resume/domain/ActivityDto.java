package com.ks.resumeproject.resume.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class ActivityDto {
    private BigInteger id;
    private String randomId;
    private BigInteger activityId;
    private int activityKey;
    private Date activityStartDate;
    private Date activityEndDate;
    private String activityOrg;
    private String activityContent;
}
