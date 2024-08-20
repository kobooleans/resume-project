package com.ks.resumeproject.portfolio.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PortfolioDetailDto {
    private BigInteger detailId;
    private BigInteger id;
    private String randomId;
    private BigInteger portId;
    private int detailSeq;
    private String detailDivision;
    private String detailTitle;
    private String detailContent;
}
