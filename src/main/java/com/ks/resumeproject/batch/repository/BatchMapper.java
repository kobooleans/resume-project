package com.ks.resumeproject.batch.repository;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface BatchMapper {
    List<AccountDto> selectDelAccountList();

    List<FileDto> selectDelFileList();

    void deleteAccountMyPage(AccountDto accountDto);

    void deleteResumeEdu(AccountDto accountDto);

    void deleteResumeCareer(AccountDto accountDto);

    void deleteResumeActivity(AccountDto accountDto);

    void deleteResumeInfo(AccountDto accountDto);

    void deleteResumeSkillDetail(AccountDto accountDto);

    void deleteResumeSkill(AccountDto accountDto);

    void deleteResumeLangTest(AccountDto accountDto);

    void deleteResumeLicense(AccountDto accountDto);

    void deleteResumeAward(AccountDto accountDto);

    void deleteResumeCoverLetter(AccountDto accountDto);

    void deleteResume(AccountDto accountDto);

    void deletePortfolioDetail(AccountDto accountDto);

    void deletePortfolio(AccountDto accountDto);

    void deletePortfolioSkillDetail(AccountDto accountDto);

    void deletePortfolioSkill(AccountDto accountDto);

    void deleteCategory(AccountDto accountDto);

    void deleteMainInfo(AccountDto accountDto);

    void deleteFile(BigInteger fileId);

    void deleteAccount(AccountDto accountDto);

    List<PortfolioDto> selectDelPortFile(AccountDto accountDto);

    void deletePortImgFile(PortfolioDto portfolioDto);

    void deletePortFile(PortfolioDto portfolioDto);
}