package com.ks.resumeproject.portfolio.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CategoryDto {
    private BigInteger categoryId;
    private BigInteger id;
    private String randomId;
    private String categoryNm;
}
