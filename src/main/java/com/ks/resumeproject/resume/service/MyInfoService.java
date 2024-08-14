package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.MyinfoDto;
import com.ks.resumeproject.security.domain.AccountDto;

public interface MyInfoService {
    MyinfoDto getMyInfo(AccountDto accountDto);

    void setMyInfo(MyinfoDto myinfoDto);
}
