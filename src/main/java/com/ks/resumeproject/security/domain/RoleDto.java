package com.ks.resumeproject.security.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RoleDto {
    private BigInteger id;
    private String roleType;
    private String roleDesc;
    private String isExpression;
    private String useYn;
}
