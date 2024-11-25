package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.MyinfoDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyInfoMapper {
    MyinfoDto getMyInfo(AccountDto accountDto);

    void insertMyInfo(MyinfoDto myinfoDto);

    void setMyInfo(MyinfoDto myinfoDto);
}
