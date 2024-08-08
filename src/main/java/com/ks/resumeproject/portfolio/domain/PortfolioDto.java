package com.ks.resumeproject.portfolio.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class PortfolioDto {
    private BigInteger id;
    private String randomId;
    private BigInteger portId;
    private String title;
    private String content;
    private BigInteger categoryId;
    private BigInteger fileId;
    private Date startYmd;
    private Date endYmd;
}
