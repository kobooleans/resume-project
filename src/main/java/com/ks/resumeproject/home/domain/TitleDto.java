package com.ks.resumeproject.home.domain;

import lombok.Data;

import java.math.BigInteger;
@Data
public class TitleDto {
    private BigInteger id;
    private String randomId;
    private String title;
    private String subtitle;
    private boolean resumeYn;
}
