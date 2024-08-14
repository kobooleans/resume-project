package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class MyinfoDto {
    private BigInteger id;
    private String randomId;
    private String name;
    private String firstname;
    private String lastname;
    private String job;
    private String email;
    private String call;
    private String location;
}
