package com.ks.resumeproject.profile.service;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface ProfileService {
    List<FileDto> getProfile(AccountDto accountDto);

    void setProfile(AccountDto accountDto);
}
