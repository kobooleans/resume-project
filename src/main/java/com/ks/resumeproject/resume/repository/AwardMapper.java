package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.AwardDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AwardMapper {
    List<AwardDto> getAwardList(AccountDto accountDto);
    List<AwardDto> getLicenseList(AccountDto accountDto);
    List<AwardDto> getLangTestList(AccountDto accountDto);

    int insertAward(AwardDto awardDto);
    int insertLicense(AwardDto awardDto);
    int insertLangTest(AwardDto awardDto);

    int updateAward(AwardDto awardDto);
    int updateLicense(AwardDto awardDto);
    int updateLangTest(AwardDto awardDto);

    int deleteAward(AwardDto awardDto);
    int deleteLicense(AwardDto awardDto);
    int deleteLangTest(AwardDto awardDto);

    int updateAwardList(AwardDto awardDto);
    int updateLicenseList(AwardDto awardDto);
    int updateLangTestList(AwardDto awardDto);
}
