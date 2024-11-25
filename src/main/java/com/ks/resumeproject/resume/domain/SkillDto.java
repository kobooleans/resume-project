package com.ks.resumeproject.resume.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class SkillDto implements Serializable {

    private BigInteger skillId;
    private String skillImgId;
    private BigInteger id;
    private String randomId;
    private String skillUri;
    private String skillTitle;
    private String skillImgTitle;
    private String skillHex;

}
