package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.CoverLetterDto;
import com.ks.resumeproject.resume.service.CoverLetterService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface CoverLetterMapper {
    List<CoverLetterDto> getCoverLetterList(AccountDto accountDto);

    int insertCoverLetter(CoverLetterDto coverLetterDto);

    int updateCoverLetter(CoverLetterDto coverLetterDto);

    int deleteCoverLetter(CoverLetterDto coverLetterDto);

    int updateCoverLetterList(CoverLetterDto coverLetterDto);
}
