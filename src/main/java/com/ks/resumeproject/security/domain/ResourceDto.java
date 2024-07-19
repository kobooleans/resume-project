package com.ks.resumeproject.security.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ResourceDto {
    private BigInteger id;
    private String resourceName;
    private String httpMethod;
    private int orderNum;
    private String resourceType;
    private String roleType;
    private String useYn;
}
