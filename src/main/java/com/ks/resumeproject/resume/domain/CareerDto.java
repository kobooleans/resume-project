package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class CareerDto {
    private String username;
    private BigInteger careerId;
    private BigInteger id;
    private String randomId;
    private Date startDay;
    private Date endDay;
    private String companyName;
    private String role;
    private String title;
    private String position;
    private String job;
    private String salary;
    private String location;
    private String content;
}
