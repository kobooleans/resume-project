package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.SkillDto;
import com.ks.resumeproject.resume.domain.SkillSetDto;
import com.ks.resumeproject.resume.service.SkillService;
import com.ks.resumeproject.resume.repository.SkillMapper;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillMapper skillMapper;
    private final SecurityUtil securityUtil;
    private final SecurityService securityService;

    @Override
    public List<SkillSetDto> getSkillSet(AccountDto accountDto) {
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

        List<SkillSetDto> result = new ArrayList<>();
        List<SkillSetDto> skillSetDtos = skillMapper.getSkillSet(accountDto);

        for(SkillSetDto skillSetDto : skillSetDtos){

            skillSetDto.setSkills(skillMapper.getSkillDetailSet(skillSetDto));

            result.add(skillSetDto);
        }

        return result;
    }

    @Override
    @Transactional
    public void insertSkillSet(SkillSetDto skillDtos){
        skillDtos.setId(securityUtil.getAccount().getAccountDto().getId());
        SkillDto skillDto = skillDtos.getSkills().get(0);

        BigInteger id = skillDtos.getId();
        String randomId = skillDtos.getRandomId();

        skillDto.setId(id);
        skillDto.setRandomId(randomId);
        skillDto.setSkillTitle(skillDtos.getSkillTitle());

        BigInteger skillId = skillMapper.selectSkillId(skillDto);
        skillDto.setSkillId(skillId);

        skillMapper.insertSkill(skillDto);

        skillDtos.getSkills().forEach(skill -> {
            skill.setId(id);
            skill.setRandomId(randomId);
            skill.setSkillId(skillId);

            skillMapper.insertSkillDetail(skill);
        });
    }

    @Override
    @Transactional
    public void deleteSkillSet(SkillSetDto skillDtos) {
        skillDtos.setId(securityUtil.getAccount().getAccountDto().getId());
        skillMapper.deleteSkillSet(skillDtos);
        skillMapper.deleteSkillDetailSet(skillDtos);

    }
}
