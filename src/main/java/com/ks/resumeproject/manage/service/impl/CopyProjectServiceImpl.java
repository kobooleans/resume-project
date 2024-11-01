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
            copyProjectMapper.copyResumeSkillDetail(projectDto);
            copyProjectMapper.copyResumeSkill(projectDto);
            copyProjectMapper.copyResumeLangTest(projectDto);
            copyProjectMapper.copyResumeLicense(projectDto);
            copyProjectMapper.copyResumeAward(projectDto);
            copyProjectMapper.copyResumeCoverLetter(projectDto);

            // 3. PORTFOLIO 복제
            copyProjectMapper.copyPortfolio(projectDto);
            copyProjectMapper.copyPortfolioDetail(projectDto);
            copyProjectMapper.copyPortfolioSkill(projectDto);
            copyProjectMapper.copyPortfolioSkillDetail(projectDto);

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
}
