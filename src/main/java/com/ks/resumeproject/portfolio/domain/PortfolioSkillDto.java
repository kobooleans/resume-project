package com.ks.resumeproject.portfolio.domain;

import com.ks.resumeproject.resume.domain.SkillDto;

import java.math.BigInteger;
import java.util.List;

public class PortfolioSkillDto {
    private BigInteger id;
    private String username;
    private String randomId;
    private BigInteger port_id;
    private BigInteger skillId;
    private String skillTitle;
    private List<PortfolioSkillDetailDto> skills;
}
