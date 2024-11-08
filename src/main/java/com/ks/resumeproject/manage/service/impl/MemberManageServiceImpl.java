package com.ks.resumeproject.manage.service.impl;

import com.ks.resumeproject.error.domain.ErrorDto;
import com.ks.resumeproject.error.exception.CustomCodeException;
import com.ks.resumeproject.error.exception.CustomException;
import com.ks.resumeproject.manage.domain.MemberManageDto;
import com.ks.resumeproject.manage.repository.MemberManageMapper;
import com.ks.resumeproject.manage.service.MemberManageService;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.repository.SecurityMapper;
import com.ks.resumeproject.users.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {

    private final MemberManageMapper memberManageMapper;
    private final SecurityMapper securityMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;

    @Override
    public void updatePw(MemberManageDto memberManageDto) {
        String msg = "";

        //현재 비밀번호와 비밀번호가 맞는지 확인.
        AccountDto accountDto = securityMapper.selectAccount(securityUtil.getAccount().getAccountDto().getUsername());

        if( !passwordEncoder.matches(memberManageDto.getCurrentPw(), accountDto.getPassword())){
            msg = "현재 비밀번호가 맞지 않습니다.";
            throw new CustomCodeException(new ErrorDto("", msg));
        }

        if( memberManageDto.getCurrentPw().equals(memberManageDto.getNewPw())){
            msg = "새 비밀번호가 현재 비밀번호와 동일합니다.";
            throw new CustomCodeException(new ErrorDto("", msg));
        }

        memberManageDto.setId(accountDto.getId());
        memberManageDto.setUsername(accountDto.getUsername());
        memberManageDto.setNewPw(passwordEncoder.encode(memberManageDto.getNewPw()));
        memberManageMapper.updatePw(memberManageDto);
    }

    @Override
    public void updateUseYnId() {
        AccountDto accountDto = securityUtil.getAccount().getAccountDto();

        /*account 테이블의 use_yn을 N처리*/
        memberManageMapper.updateUseYnId(accountDto);

    }
}
