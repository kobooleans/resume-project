package com.ks.resumeproject.portfolio.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PortfolioSkillDetailDto {
    private BigInteger skillId;
    private String skillImgId;
    private BigInteger id;
    private String randomId;
    private BigInteger portId;
    private String skillUri;
    private String skillTitle;
    private String skillImgTitle;
    private String skillHex;

}
