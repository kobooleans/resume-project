package com.ks.resumeproject.home.service;

import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface HomeService {

    TitleDto selectTitle(AccountDto accountDto);

    void updateTitle(TitleDto titleDto);

    List<SiteInfoDto> selectSiteInfo(AccountDto accountDto);
}
