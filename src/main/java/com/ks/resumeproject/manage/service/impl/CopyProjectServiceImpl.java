package com.ks.resumeproject.manage.service.impl;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.manage.repository.CopyProjectMapper;
import com.ks.resumeproject.manage.service.CopyProjectService;
import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.multi.repository.MultiFileMapper;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.util.ComUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyProjectServiceImpl implements CopyProjectService {

    private final CopyProjectMapper copyProjectMapper;
    private final MultiFileMapper multiFileMapper;

    private final SecurityUtil securityUtil;
    private final ComUtil comUtil;

    @Override
    public List<ProjectDto> selectProjectList() {
        return copyProjectMapper.selectProjectList(securityUtil.getAccount().getAccountDto());
    }

    @Override
    @Transactional
    public void copyProject(List<ProjectDto> projectDtos) {

        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        // 프로젝트 복제
        for (ProjectDto projectDto : projectDtos) {
            String randomVal = comUtil.addRandomVal(10);

            projectDto.setId(accountDto.getId());
            projectDto.setUsername(accountDto.getUsername());
            projectDto.setCopyRandomId(randomVal);

            // 1. ACCOUNT_MY_PAGE 복제
            copyProjectMapper.copyAccountMyPage(projectDto);

            // 2. RESUME 복제
            copyProjectMapper.copyResume(projectDto);
            copyProjectMapper.copyResumeEdu(projectDto);
            copyProjectMapper.copyResumeCareer(projectDto);
            copyProjectMapper.copyResumeActivity(projectDto);
            copyProjectMapper.copyResumeInfo(projectDto);
            copyProjectMapper.copyResumeSkill(projectDto);
            copyProjectMapper.copyResumeSkillDetail(projectDto);
            copyProjectMapper.copyResumeLangTest(projectDto);
            copyProjectMapper.copyResumeLicense(projectDto);
            copyProjectMapper.copyResumeAward(projectDto);
            copyProjectMapper.copyResumeCoverLetter(projectDto);

            // 3. PORTFOLIO 복제
            copyProjectMapper.copyPortfolio(projectDto);
            copyProjectMapper.copyPortfolioDetail(projectDto);
            copyProjectMapper.copyPortfolioSkill(projectDto);
            copyProjectMapper.copyPortfolioSkillDetail(projectDto);
            copyProjectMapper.copyCategory(projectDto);

            // 4. MAIN_INFO 복제
            copyProjectMapper.copyMainInfo(projectDto);

            // 5. FILE 관련사항 복제

            /* potrolio 첨부파일 복제 */
            List<FileDto> fileDtos = copyProjectMapper.selectPortfolioFileList(projectDto);
            for (FileDto fileDto : fileDtos) {
                BigInteger result = multiFileMapper.getFileId();

                projectDto.setFileId(fileDto.getFileId());
                projectDto.setCopyFileId(result);

                copyProjectMapper.copyFile(projectDto);
                copyProjectMapper.updatePortfolioFile(projectDto);
            }

            List<FileDto> fileImgDtos = copyProjectMapper.selectPortfolioImgFileList(projectDto);
            for (FileDto fileDto : fileImgDtos) {
                BigInteger result = multiFileMapper.getFileId();

                projectDto.setFileId(fileDto.getFileId());
                projectDto.setCopyFileId(result);
                copyProjectMapper.copyFile(projectDto);
                copyProjectMapper.updatePortfolioImgFile(projectDto);
            }
        }
    }

    @Override
    @Transactional
    public void saveProject(List<ProjectDto> projectDtos) {
        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        for (ProjectDto projectDto : projectDtos) {
            projectDto.setId(accountDto.getId());
            projectDto.setUsername(accountDto.getUsername());

            copyProjectMapper.updateAccountMyPage(projectDto);
        }
    }

    @Override
    @Transactional
    public void deleteProject(List<ProjectDto> projectDtos) {
        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        for (ProjectDto projectDto : projectDtos) {
            projectDto.setId(accountDto.getId());

            // 1. FILE 관련사항 삭제

            /* potrolio 첨부파일 삭제 */
            List<FileDto> fileDtos = copyProjectMapper.selectPortfolioFileList(projectDto);
            for (FileDto fileDto : fileDtos) {
                projectDto.setFileId(fileDto.getFileId());
                projectDto.setCopyFileId(fileDto.getFileId());

                copyProjectMapper.updateFile(projectDto);
            }

            List<FileDto> fileImgDtos = copyProjectMapper.selectPortfolioImgFileList(projectDto);
            for (FileDto fileDto : fileImgDtos) {
                projectDto.setFileId(fileDto.getFileId());
                projectDto.setCopyFileId(fileDto.getFileId());
                copyProjectMapper.updateFile(projectDto);
            }

            // 2. ACCOUNT_MY_PAGE 복제
            copyProjectMapper.deleteAccountMyPage(projectDto);

            // 3. RESUME 복제
            copyProjectMapper.deleteResumeEdu(projectDto);
            copyProjectMapper.deleteResumeCareer(projectDto);
            copyProjectMapper.deleteResumeActivity(projectDto);
            copyProjectMapper.deleteResumeInfo(projectDto);
            copyProjectMapper.deleteResumeSkillDetail(projectDto);
            copyProjectMapper.deleteResumeSkill(projectDto);
            copyProjectMapper.deleteResumeLangTest(projectDto);
            copyProjectMapper.deleteResumeLicense(projectDto);
            copyProjectMapper.deleteResumeAward(projectDto);
            copyProjectMapper.deleteResumeCoverLetter(projectDto);
            copyProjectMapper.deleteResume(projectDto);

            // 4. PORTFOLIO 복제
            copyProjectMapper.deletePortfolioDetail(projectDto);
            copyProjectMapper.deletePortfolio(projectDto);
            copyProjectMapper.deletePortfolioSkillDetail(projectDto);
            copyProjectMapper.deletePortfolioSkill(projectDto);
            copyProjectMapper.deleteCategory(projectDto);

            // 5. MAIN_INFO 삭제
            copyProjectMapper.deleteMainInfo(projectDto);
        }
    }

    @Override
    public void setMainProject(ProjectDto projectDto) {

        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        projectDto.setUsername(accountDto.getUsername());
        projectDto.setMainYn(true);

        copyProjectMapper.setMainDelProject(projectDto);
        copyProjectMapper.setMainProject(projectDto);
    }
}
