package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.CareerDto;
import com.ks.resumeproject.resume.domain.CoverLetterDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.repository.CareerMapper;
import com.ks.resumeproject.resume.repository.CoverLetterMapper;
import com.ks.resumeproject.resume.repository.ResumeMapper;
import com.ks.resumeproject.resume.service.CoverLetterService;
import com.ks.resumeproject.resume.service.ResumeService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoverLetterServiceImpl implements CoverLetterService {

    private final CoverLetterMapper coverLetterMapper;
    private final SecurityService securityService;
    private final SecurityUtil securityUtil;

    @Override
    public List<CoverLetterDto> getCoverLetterList(AccountDto accountDto) {

        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());
        return coverLetterMapper.getCoverLetterList(accountDto);
    }

    @Override
    public int insertCoverLetter(CoverLetterDto coverLetterDto) {
        coverLetterDto.setId(securityUtil.getAccount().getAccountDto().getId());
        return coverLetterMapper.insertCoverLetter(coverLetterDto);
    }

    @Override
    public int updateCoverLetter(CoverLetterDto coverLetterDto) {
        coverLetterDto.setId(securityUtil.getAccount().getAccountDto().getId());
        return coverLetterMapper.updateCoverLetter(coverLetterDto);
    }

    @Override
    public int deleteCoverLetter(CoverLetterDto coverLetterDto) {
        coverLetterDto.setId(securityUtil.getAccount().getAccountDto().getId());
        return coverLetterMapper.deleteCoverLetter(coverLetterDto);
    }
}
