package com.ks.resumeproject.manage.service;

import com.ks.resumeproject.manage.domain.ProjectDto;

import java.util.List;

public interface CopyProjectService {

    List<ProjectDto> selectProjectList();

    void copyProject(List<ProjectDto> projectDto);

    void saveProject(List<ProjectDto> projectDto);

    void deleteProject(List<ProjectDto> projectDto);

    void setMainProject(ProjectDto projectDto);
}
