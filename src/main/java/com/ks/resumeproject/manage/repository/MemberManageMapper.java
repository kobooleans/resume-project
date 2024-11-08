package com.ks.resumeproject.manage.repository;

import com.ks.resumeproject.manage.domain.MemberManageDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberManageMapper {
    void updatePw(MemberManageDto memberManageDto);

    void updateUseYnId(AccountDto accountDto);
}
