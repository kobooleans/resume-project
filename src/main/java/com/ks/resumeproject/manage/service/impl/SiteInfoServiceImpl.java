package com.ks.resumeproject.manage.service.impl;

import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.manage.repository.SiteInfoMapper;
import com.ks.resumeproject.manage.service.SiteInfoService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteInfoServiceImpl implements SiteInfoService {

    private final SiteInfoMapper siteInfoMapper;
    private final SecurityUtil securityUtil;

    @Override
    public void insertFooterSite(SiteInfoDto siteInfoDto) {
        AccountDto dto = securityUtil.getAccount().getAccountDto();
        siteInfoDto.setId(dto.getId());

        siteInfoMapper.insertFooterSite(siteInfoDto);
    }

    @Override
    public List<SiteInfoDto> getFooterSiteList() {

        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        return siteInfoMapper.getFooterSiteList(accountDto);
    }

    @Override
    public void deleteFooterSite(List<SiteInfoDto> siteInfoDto) {

        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        for (SiteInfoDto dto : siteInfoDto) {
            dto.setSiteId(dto.getId());
            dto.setId(accountDto.getId());

            siteInfoMapper.deleteFooterSite(dto);
        }
    }

    @Override
    public void updateFooterSite(SiteInfoDto siteInfoDto) {
        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        siteInfoDto.setId(accountDto.getId());

        siteInfoMapper.updateFooterSite(siteInfoDto);

    }
}
