package com.ks.resumeproject.manage.repository;

import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.domain.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteInfoMapper {
    void insertFooterSite(SiteInfoDto siteInfoDto);

    List<SiteInfoDto> getFooterSiteList(AccountDto accountDto);

    void deleteFooterSite(SiteInfoDto dto);

    void updateFooterSite(SiteInfoDto siteInfoDto);

    List<PageDto> selectPage(AccountDto accountDto);
}
