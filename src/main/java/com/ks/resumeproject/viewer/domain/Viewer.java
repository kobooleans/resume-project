package com.ks.resumeproject.viewer.domain;

import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.portfolio.domain.PortfolioDto;
import com.ks.resumeproject.portfolio.domain.PortfolioSkillDetailDto;
import com.ks.resumeproject.resume.domain.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Viewer {

    private ResumeDto resumeDto; // 이력서 내역 확인
    private MyinfoDto myinfoDto; // 내 개인정보 가져오기

    private Boolean isSideProjectYn;

    private List<SkillSetDto> skillSetDtos;
    private List<CareerDto> careerDtos;
    private List<EduDto> eduDtos;
    private Map<String, Object> awardDtos;
    private List<ActivityDto> activityDtos;
    private List<CoverLetterDto> coverLetterDtos;

    private List<PortfolioDto> portfolioDtos;
    private List<PortfolioSkillDetailDto> portfolioSkillDetailDtos;

    private List<SiteInfoDto> siteInfoDtos;
}
