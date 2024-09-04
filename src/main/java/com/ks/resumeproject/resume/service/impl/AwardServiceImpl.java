package com.ks.resumeproject.resume.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.resumeproject.resume.domain.AwardDto;
import com.ks.resumeproject.resume.repository.AwardMapper;
import com.ks.resumeproject.resume.service.AwardService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardMapper awardMapper;
    private final SecurityService securityService;
    private final SecurityUtil securityUtil;

    @Override
    public Map<String, Object> getAwardList(AccountDto accountDto) {

        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

        List<AwardDto> awardList = awardMapper.getAwardList(accountDto);
        List<AwardDto> licenseList = awardMapper.getLicenseList(accountDto);
        List<AwardDto> langTestList = awardMapper.getLangTestList(accountDto);
        Map<String, Object> rst = new HashMap<>();
        rst.put("awardList", awardList);
        rst.put("licenseList", licenseList);
        rst.put("langTestList", langTestList);
        return rst;
    }

    @Override
    public int insertAward(AwardDto awardDto) {
        awardDto.setId(securityUtil.getAccount().getAccountDto().getId());
        String awardKey = awardDto.getAwardKey();
        int rtn = 0;
        if(awardKey != null){
            rtn = switch (awardKey) {
                case "award" -> awardMapper.insertAward(awardDto);
                case "license" -> awardMapper.insertLicense(awardDto);
                case "lang" -> awardMapper.insertLangTest(awardDto);
                default -> rtn;
            };
        }
        return rtn;
    }

    @Override
    public int updateAward(AwardDto awardDto) {
        awardDto.setId(securityUtil.getAccount().getAccountDto().getId());
        String awardKey = awardDto.getAwardKey();
        int rst = 0;
        if(awardKey != null){
            rst = switch (awardKey) {
                case "award" -> awardMapper.updateAward(awardDto);
                case "license" -> awardMapper.updateLicense(awardDto);
                case "lang" -> awardMapper.updateLangTest(awardDto);
                default -> rst;
            };
        }
        return rst;
    }

    @Override
    public int deleteAward(AwardDto awardDto) {
        awardDto.setId(securityUtil.getAccount().getAccountDto().getId());

        String awardKey = awardDto.getAwardKey();
        int rst = 0;
        if (awardKey != null) {
            rst = switch (awardKey) {
                case "award" -> awardMapper.deleteAward(awardDto);
                case "license" -> awardMapper.deleteLicense(awardDto);
                case "lang" -> awardMapper.deleteLangTest(awardDto);
                default -> rst;
            };
        }
        return rst;
    }

    @Override
    @Transactional
    public int updateAwardList(List<Map<String, Object>> updateList) {

        int rst = 0;
        for(Map<String, Object> updateMap : updateList) {
           
            updateMap.put("id", securityUtil.getAccount().getAccountDto().getId());
            ObjectMapper mapper = new ObjectMapper();
            AwardDto awardDto = mapper.convertValue(updateMap, AwardDto.class);

            String awardKey = awardDto.getAwardKey();
            int rtn = 0;
            if (awardKey != null) {
                rst = switch (awardKey) {
                    case "award" -> awardMapper.updateAwardList(awardDto);
                    case "license" -> awardMapper.updateLicenseList(awardDto);
                    case "lang" -> awardMapper.updateLangTestList(awardDto);
                    default -> rst;
                };
            }
        }
        return rst;
    }
}
