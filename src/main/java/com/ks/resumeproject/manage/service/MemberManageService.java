package com.ks.resumeproject.manage.service;

import com.ks.resumeproject.manage.domain.MemberManageDto;

public interface MemberManageService {
    void updatePw(MemberManageDto memberManageDto);

    void updateUseYnId();
}
