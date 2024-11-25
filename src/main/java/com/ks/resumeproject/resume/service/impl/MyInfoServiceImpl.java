package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.MyinfoDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.repository.MyInfoMapper;
import com.ks.resumeproject.resume.repository.ResumeMapper;
import com.ks.resumeproject.resume.service.MyInfoService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyInfoServiceImpl implements MyInfoService {

    private final MyInfoMapper myInfoMapper;
    private final SecurityService securityService;
    private final ResumeMapper resumeMapper;

    @Override
    public MyinfoDto getMyInfo(AccountDto accountDto) {
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

        ResumeDto resumeDto = resumeMapper.getResumeSet(accountDto);


        /** 만약 처음 resume으로 접근했을 경우 모두 false로 생성 */
        if(resumeDto == null){

            resumeDto = new ResumeDto();
            resumeDto.setId(accountDto.getId());
            resumeDto.setRandomId(accountDto.getRandomId());
            resumeDto.setSkillYn(false);
            resumeDto.setCareerYn(false);
            resumeDto.setEduYn(false);
            resumeDto.setAwardYn(false);
            resumeDto.setActionYn(false);
            resumeDto.setCoverLetterYn(false);

            resumeMapper.setResumeSet(resumeDto);

        }

        MyinfoDto myinfoDto = myInfoMapper.getMyInfo(accountDto);

        if(myinfoDto == null){
            myinfoDto = new MyinfoDto();
            myinfoDto.setId(accountDto.getId());
            myinfoDto.setRandomId(accountDto.getRandomId());

            myInfoMapper.insertMyInfo(myinfoDto);
        }


        return myinfoDto;
    }

    @Override
    public void setMyInfo(MyinfoDto myinfoDto) {
        myInfoMapper.setMyInfo(myinfoDto);
    }

}
