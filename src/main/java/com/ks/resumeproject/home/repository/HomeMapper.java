package com.ks.resumeproject.home.repository;

import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {
    TitleDto selectTitle(AccountDto accountDto);

    void insertTitle(TitleDto titleDto);

    void updateTitle(TitleDto titleDto);

    List<SiteInfoDto> selectSiteInfo(AccountDto accountDto);
}
