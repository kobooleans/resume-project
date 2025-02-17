package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.portfolio.domain.CategoryDto;
import com.ks.resumeproject.resume.domain.ResumeDto;
import com.ks.resumeproject.resume.repository.ResumeMapper;
import com.ks.resumeproject.resume.service.ResumeService;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeMapper resumeMapper;
    private final SecurityService securityService;

    @Override
    public ResumeDto getResumeSet(AccountDto accountDto) {
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
        }

        return resumeDto;
    }

    @Override
    public void setResumeItem(ResumeDto resumeDto) {
        switch (resumeDto.getType()){
            case "skill" :
                resumeDto.setSkillYn(resumeDto.getIsYn().equals("Y"));
                break;
            case "career" :
                resumeDto.setCareerYn(resumeDto.getIsYn().equals("Y"));
                break;
            case "edu" :
                resumeDto.setEduYn(resumeDto.getIsYn().equals("Y"));
                break;
            case "award":
                resumeDto.setAwardYn(resumeDto.getIsYn().equals("Y"));
                break;
            case "action" :
                resumeDto.setActionYn(resumeDto.getIsYn().equals("Y"));
                break;
            case "skillImgYn" :
                resumeDto.setSkillImgYn(resumeDto.getIsYn().equals("Y"));
                break;
            case "coverLetter":
                resumeDto.setCoverLetterYn(resumeDto.getIsYn().equals("Y"));
                break;
        }

        resumeMapper.setResumeItem(resumeDto);
    }

}
