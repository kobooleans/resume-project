package com.ks.resumeproject.home.service;

import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.security.domain.AccountDto;

public interface HomeService {

    TitleDto selectTitle(AccountDto accountDto);

    void updateTitle(TitleDto titleDto);

}
