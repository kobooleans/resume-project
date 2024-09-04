package com.ks.resumeproject.profile.service.impl;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.multi.service.MultiFileService;
import com.ks.resumeproject.profile.repository.ProfileMapper;
import com.ks.resumeproject.profile.service.ProfileService;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;
    private final MultiFileService multiFileService;
    private final SecurityUtil securityUtil;

    @Override
    public List<FileDto> getProfile(AccountDto accountDto) {

        BigInteger fileId = profileMapper.getProfile(accountDto);

        return multiFileService.getFileList(fileId);
    }

    @Override
    public void setProfile(AccountDto accountDto) {
        AccountContext context = securityUtil.getAccount();
        AccountDto account = context.getAccountDto();

        account.setFileId(accountDto.getFileId());

        /*이전에 등록한 프로필파일 USE_YN 'N' 처리*/
        profileMapper.updateProfileFileUseYn(account);

        int count = profileMapper.setProfile(account);

        if(count > 0){
            multiFileService.setProfile(account);
        }
    }
}
