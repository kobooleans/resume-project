package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
public class SkillSetDto implements Serializable {
    private BigInteger id;
    private String username;
    private String randomId;
    private BigInteger skillId;
    private String skillTitle;
    private List<SkillDto> skills;
}
