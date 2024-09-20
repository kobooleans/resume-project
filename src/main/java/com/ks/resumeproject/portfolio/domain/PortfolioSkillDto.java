package com.ks.resumeproject.portfolio.domain;

import com.ks.resumeproject.resume.domain.SkillDto;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class PortfolioSkillDto {
    private BigInteger id;
    private String username;
    private String randomId;
    private BigInteger portId;
    private BigInteger skillId;
    private String skillTitle;
}
