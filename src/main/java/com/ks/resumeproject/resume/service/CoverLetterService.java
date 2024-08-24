package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.CoverLetterDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface CoverLetterService {
    List<CoverLetterDto> getCoverLetterList(AccountDto accountDto);

    int insertCoverLetter(CoverLetterDto coverLetterDto);

    int updateCoverLetter(CoverLetterDto coverLetterDto);

    int deleteCoverLetter(CoverLetterDto coverLetterDto);
}
