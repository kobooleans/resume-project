package com.ks.resumeproject.manage.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SiteInfoDto {

    private BigInteger id;
    private BigInteger siteId;
    private String siteTitle;
    private String siteIcon;
    private String siteUrl;
    private String siteContent;
    private String pagesData;
}
