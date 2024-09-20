package com.ks.resumeproject.portfolio.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class PortfolioDto {
    private BigInteger id;
    private String username;
    private String randomId;
    private BigInteger portId;
    private String title;
    private String content;
    private BigInteger categoryId;
    private String categoryNm;
    private BigInteger fileId;
    private byte[] file;
    private Date startYmd;
    private Date endYmd;
    private List<PortfolioDetailDto> detailList;

    private BigInteger skillId;
    private String skillTitle;
    private List<PortfolioSkillDetailDto> skills;


}
