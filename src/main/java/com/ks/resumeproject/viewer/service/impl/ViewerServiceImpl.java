package com.ks.resumeproject.viewer.service.impl;

import com.ks.resumeproject.home.service.HomeService;
import com.ks.resumeproject.manage.service.SiteInfoService;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.portfolio.domain.PortfolioSkillDetailDto;
import com.ks.resumeproject.portfolio.service.PortfolioService;
import com.ks.resumeproject.resume.domain.MyinfoDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.service.*;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import com.ks.resumeproject.viewer.domain.Viewer;
import com.ks.resumeproject.viewer.repository.ViewerMapper;
import com.ks.resumeproject.viewer.service.ViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewerServiceImpl implements ViewerService {

    private final SecurityService securityService;

    private final ViewerMapper viewerMapper;

    private final ResumeService resumeService;
    private final MyInfoService myInfoService;

    private final SkillService skillService;
    private final CareerService careerService;

    private final HomeService homeService;

    private final EduService eduService;
    private final AwardService awardService;
    private final ActivityService activityService;
    private final CoverLetterService coverLetterService;

    private final PortfolioService portfolioService;

    @Override
    public String selectRandomId(AccountDto accountDto) {
        return viewerMapper.selectRandomId(accountDto);
    }

    @Override
    public Viewer cvProjectViewerList(AccountDto accountDto) {

        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

        Viewer viewer = new Viewer();

        // 이력서 설정정보 가져오기
        ResumeDto resumeSet = resumeService.getResumeSet(accountDto);
        viewer.setResumeDto(resumeSet);

        // 메인 정보 가져오기
        viewer.setMyinfoDto(myInfoService.getMyInfo(accountDto));

        if(resumeSet.isSkillYn()) viewer.setSkillSetDtos(skillService.getSkillSet(accountDto));
        if(resumeSet.isCareerYn()) viewer.setCareerDtos(careerService.getCareerList(accountDto));
        if(resumeSet.isEduYn()) viewer.setEduDtos(eduService.selectEdu(accountDto));
        if(resumeSet.isAwardYn()) viewer.setAwardDtos(awardService.getAwardList(accountDto));
        if(resumeSet.isActionYn()) viewer.setActivityDtos(activityService.getActivityList(accountDto.getUsername(), accountDto.getRandomId()));
        if(resumeSet.isCoverLetterYn()) viewer.setCoverLetterDtos(coverLetterService.getCoverLetterList(accountDto));


        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setUsername(accountDto.getUsername());
        portfolioDto.setRandomId(accountDto.getRandomId());
        portfolioDto.setId(accountDto.getId());
        portfolioDto.setCategoryId(new BigInteger("0"));

        viewer.setIsSideProjectYn(careerService.isSideProjectYn(accountDto));
        viewer.setPortfolioDtos(portfolioService.portfolioAllList(portfolioDto));
        viewer.setPortfolioSkillDetailDtos(portfolioService.selectSkillDtailDtos(portfolioDto));

        viewer.setSiteInfoDtos(homeService.selectSiteInfo(accountDto));

        return viewer;
    }
}
