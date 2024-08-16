package com.ks.resumeproject.home.repository;

import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {
    TitleDto selectTitle(AccountDto accountDto);

    void insertTitle(TitleDto titleDto);

    void updateTitle(TitleDto titleDto);
}
