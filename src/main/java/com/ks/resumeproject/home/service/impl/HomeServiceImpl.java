package com.ks.resumeproject.home.service.impl;

import com.ks.resumeproject.home.domain.TitleDto;
import com.ks.resumeproject.home.repository.HomeMapper;
import com.ks.resumeproject.home.service.HomeService;
import com.ks.resumeproject.manage.domain.SiteInfoDto;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final SecurityService securityService;
    private final HomeMapper homeMapper;

    @Override
    public TitleDto selectTitle(AccountDto accountDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());
        TitleDto titleDto = homeMapper.selectTitle(accountDto);
        if (titleDto == null) {
            titleDto = new TitleDto();
            titleDto.setId(accountDto.getId());
            titleDto.setRandomId(accountDto.getRandomId());
            titleDto.setTitle(accountDto.getUsername()+"프로젝트에 오신 것을 환영합니다.");
            titleDto.setSubtitle("내 직업적 여정을 탐색하여 내 기술, 경험, 성과를 확인하세요.");
            titleDto.setResumeYn(true);

            homeMapper.insertTitle(titleDto);
        }
        return titleDto;
    }

    @Override
    public void updateTitle(TitleDto titleDto) {
        homeMapper.updateTitle(titleDto);
    }

    @Override
    public List<SiteInfoDto> selectSiteInfo(AccountDto accountDto) {
        return homeMapper.selectSiteInfo(accountDto);
    }
}
