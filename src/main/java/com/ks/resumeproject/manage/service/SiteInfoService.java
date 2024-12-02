package com.ks.resumeproject.manage.service;

import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.users.domain.PageDto;

import java.util.List;

public interface SiteInfoService {
    void insertFooterSite(SiteInfoDto siteInfoDto);

    List<SiteInfoDto> getFooterSiteList();

    void deleteFooterSite(List<SiteInfoDto> siteInfoDto);

    void updateFooterSite(SiteInfoDto siteInfoDto);

    List<PageDto> selectPage();
}
