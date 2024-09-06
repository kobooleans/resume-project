package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.ActivityDto;
import com.ks.resumeproject.resume.repository.ActivityMapper;
import com.ks.resumeproject.resume.service.ActivityService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final SecurityService securityService;

    private final ActivityMapper activityMapper;

    private final SecurityUtil securityUtil;

    @Override
    public List<ActivityDto> getActivityList(String username, String randomId) {

        AccountDto accountDto = securityService.selectUserAccount(username);
        accountDto.setRandomId(randomId);


        return activityMapper.getActivityList(accountDto);
    }

    @Override
    public void insertActivity(ActivityDto activityDto) {
        activityDto.setId(securityUtil.getAccount().getAccountDto().getId());

        activityMapper.insertActivity(activityDto);
    }

    @Override
    public void updateActivity(ActivityDto activityDto) {
        activityDto.setId(securityUtil.getAccount().getAccountDto().getId());

        activityMapper.updateActivity(activityDto);
    }

    @Override
    public void deleteActivity(ActivityDto activityDto) {
        activityDto.setId(securityUtil.getAccount().getAccountDto().getId());

        activityMapper.deleteActivity(activityDto);
    }
}
