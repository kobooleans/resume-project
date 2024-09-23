package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.EduDto;
import com.ks.resumeproject.resume.repository.EduMapper;
import com.ks.resumeproject.resume.service.EduService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduServiceImpl implements EduService {

    private final EduMapper eduMapper;
    private final SecurityService securityService;
    private final SecurityUtil securityUtil;

    @Override
    public List<EduDto> selectEdu(AccountDto accountDto) {
        /*로그인 시 사용자 Id에 대한 값을 가지고 있지 않기 때문에 사용자 Id에 대한 쿼리 실행 */
        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

        return eduMapper.selectEdu(accountDto);
    }

    @Override
    public void insertEdu(EduDto eduDto) {
        eduDto.setId(securityUtil.getAccount().getAccountDto().getId());

        eduMapper.insertEdu(eduDto);

    }

    @Override
    public void updateEdu(EduDto eduDto) {
        eduMapper.updateEdu(eduDto);

    }

    @Override
    public void deleteEdu(EduDto eduDto) {
        eduMapper.deleteEdu(eduDto);

    }

}
