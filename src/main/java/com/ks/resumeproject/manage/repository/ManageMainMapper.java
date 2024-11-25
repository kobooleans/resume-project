package com.ks.resumeproject.manage.repository;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageMainMapper {

    List<ProjectDto> selectProjectCalList(ProjectDto projectDto);

}
