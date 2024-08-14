package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.MyinfoDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.repository.MyInfoMapper;
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

    @Override
    public MyinfoDto getMyInfo(AccountDto accountDto) {
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

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
