package com.ks.resumeproject.manage.service;

import com.ks.resumeproject.manage.domain.ProjectDto;

import java.util.List;
import java.util.Map;

public interface ManageMainService {

    List<ProjectDto> selectProjectCalList(ProjectDto projectDto);

    Map selectManageInfo();
}
