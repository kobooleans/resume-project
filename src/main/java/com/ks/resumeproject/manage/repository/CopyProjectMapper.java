package com.ks.resumeproject.manage.repository;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CopyProjectMapper {

    List<ProjectDto> selectProjectList(AccountDto account);

    void copyAccountMyPage(ProjectDto projectDto);

    void copyResume(ProjectDto projectDto);

    void copyPortfolio(ProjectDto projectDto);

    void copyPortfolioDetail(ProjectDto projectDto);

    void copyPortfolioSkill(ProjectDto projectDto);

    void copyPortfolioSkillDetail(ProjectDto projectDto);

    void copyMainInfo(ProjectDto projectDto);

    void copyResumeEdu(ProjectDto projectDto);

    void copyResumeCareer(ProjectDto projectDto);

    void copyResumeActivity(ProjectDto projectDto);

    void copyResumeInfo(ProjectDto projectDto);

    void copyResumeSkillDetail(ProjectDto projectDto);

    void copyResumeSkill(ProjectDto projectDto);

    void copyResumeLangTest(ProjectDto projectDto);

    void copyResumeLicense(ProjectDto projectDto);

    void copyResumeAward(ProjectDto projectDto);

    void copyResumeCoverLetter(ProjectDto projectDto);

    List<FileDto> selectPortfolioFileList(ProjectDto projectDto);

    void copyFile(ProjectDto projectDto);

    void updatePortfolioFile(ProjectDto projectDto);

    List<FileDto> selectPortfolioImgFileList(ProjectDto projectDto);

    void updatePortfolioImgFile(ProjectDto projectDto);

    void copyCategory(ProjectDto projectDto);

    void updateAccountMyPage(ProjectDto projectDto);

    void updateFile(ProjectDto projectDto);

    void deleteAccountMyPage(ProjectDto projectDto);

    void deleteResumeEdu(ProjectDto projectDto);

    void deleteResumeCareer(ProjectDto projectDto);

    void deleteResumeActivity(ProjectDto projectDto);

    void deleteResumeInfo(ProjectDto projectDto);

    void deleteResumeSkillDetail(ProjectDto projectDto);

    void deleteResumeSkill(ProjectDto projectDto);

    void deleteResumeLangTest(ProjectDto projectDto);

    void deleteResumeLicense(ProjectDto projectDto);

    void deleteResumeAward(ProjectDto projectDto);

    void deleteResumeCoverLetter(ProjectDto projectDto);

    void deleteResume(ProjectDto projectDto);

    void deletePortfolioDetail(ProjectDto projectDto);

    void deletePortfolio(ProjectDto projectDto);

    void deletePortfolioSkillDetail(ProjectDto projectDto);

    void deletePortfolioSkill(ProjectDto projectDto);

    void deleteCategory(ProjectDto projectDto);

    void deleteMainInfo(ProjectDto projectDto);

    void setMainProject(ProjectDto projectDto);

    void setMainDelProject(ProjectDto projectDto);
}
