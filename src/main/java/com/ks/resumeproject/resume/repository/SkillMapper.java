package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.SkillDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SkillMapper {
    List<SkillDto> getSkillSet(AccountDto accountDto);
    List<SkillDto> getSkillDetailSet(AccountDto accountDto);
    BigInteger selectSkillId(SkillDto skillDto);
    void insertSkillDetail(SkillDto skillDto);

    void insertSkill(SkillDto skillDto);
}
