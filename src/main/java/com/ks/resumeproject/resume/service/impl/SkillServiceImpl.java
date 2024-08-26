package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.SkillDto;
import com.ks.resumeproject.resume.service.SkillService;
import com.ks.resumeproject.resume.repository.SkillMapper;
import com.ks.resumeproject.security.domain.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillMapper skillMapper;

    @Override
    public List<SkillDto> getSkillSet(AccountDto accountDto) {
        return skillMapper.getSkillSet(accountDto);
    }

    @Override
    public List<SkillDto> getSkillDetailSet(AccountDto accountDto) {
        return skillMapper.getSkillDetailSet(accountDto);
    }

    @Override
    @Transactional
    public void insertSkillSet(List<SkillDto> skillDtos){

        SkillDto skillDto = skillDtos.get(0);

        BigInteger skillId = skillMapper.selectSkillId(skillDto);

        skillDto.setSkillId(skillId);
        skillMapper.insertSkill(skillDto);

        skillDtos.forEach(skill -> {
            skill.setSkillId(skillId);
            skillMapper.insertSkillDetail(skill);
        });
    }
}
