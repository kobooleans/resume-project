package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.SkillDto;
import com.ks.resumeproject.resume.domain.SkillSetDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface SkillService {
    List<SkillSetDto> getSkillSet(AccountDto accountDto);
    void insertSkillSet(SkillSetDto skillDtos);
}
