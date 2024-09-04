package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.AwardDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;
import java.util.Map;

public interface AwardService {
    Map<String, Object> getAwardList(AccountDto accountDto);

    int insertAward(AwardDto awardDto);

    int updateAward(AwardDto awardDto);

    int deleteAward(AwardDto awardDto);

    int updateAwardList(List<Map<String, Object>> updateList);
}
