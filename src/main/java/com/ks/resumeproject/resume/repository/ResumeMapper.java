package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResumeMapper {

    ResumeDto getResumeSet(AccountDto accountDto);

    void setResumeSet(ResumeDto resumeDto);

    void setResumeItem(ResumeDto resumeDto);
}
