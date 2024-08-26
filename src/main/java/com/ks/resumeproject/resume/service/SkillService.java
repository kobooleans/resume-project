package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.SkillDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface SkillService {
    List<SkillDto> getSkillSet(AccountDto accountDto);
    void insertSkillSet(List<SkillDto> skillDtos);
    List<SkillDto> getSkillDetailSet(AccountDto accountDto);
}
