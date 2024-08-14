package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface ResumeService {
    ResumeDto getResumeSet(AccountDto accountDto);

    void setResumeItem(ResumeDto resumeDto);
}
