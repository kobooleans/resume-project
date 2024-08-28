package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.CoverLetterDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoverLetterMapper {
    List<CoverLetterDto> getCoverLetterList(AccountDto accountDto);

    int insertCoverLetter(CoverLetterDto coverLetterDto);

    int updateCoverLetter(CoverLetterDto coverLetterDto);

    int deleteCoverLetter(CoverLetterDto coverLetterDto);

    void updateCoverLetterList(CoverLetterDto coverLetterDto);
}
