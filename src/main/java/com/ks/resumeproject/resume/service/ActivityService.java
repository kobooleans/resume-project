package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.ActivityDto;

import java.util.List;

public interface ActivityService {

    List<ActivityDto> getActivityList(String username, String randomId);

    void insertActivity(ActivityDto activityDto);

    void updateActivity(ActivityDto activityDto);

    void deleteActivity(ActivityDto activityDto);
}
