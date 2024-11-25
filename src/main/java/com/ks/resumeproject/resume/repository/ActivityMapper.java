package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.ActivityDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<ActivityDto> getActivityList(AccountDto accountDto);

    void insertActivity(ActivityDto activityDto);

    void updateActivity(ActivityDto activityDto);

    void deleteActivity(ActivityDto activityDto);
}
