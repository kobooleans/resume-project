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

    ResumeDto resumeDto; // 이력서 내역 확인
    MyinfoDto myinfoDto; // 내 개인정보 가져오기

    List<SkillSetDto> skillSetDtos;
    List<CareerDto> careerDtos;
    List<EduDto> eduDtos;
    Map<String, Object> awardDtos;
    List<ActivityDto> activityDtos;
    List<CoverLetterDto> coverLetterDtos;

    List<PortfolioDto> portfolioDtos;
    List<PortfolioSkillDetailDto> portfolioSkillDetailDtos;

    List<SiteInfoDto> siteInfoDtos;
}
