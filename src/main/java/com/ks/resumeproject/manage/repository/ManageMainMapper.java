package com.ks.resumeproject.manage.repository;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManageMainMapper {

    List<ProjectDto> selectProjectCalList(ProjectDto projectDto);

    Map selectManageInfo(AccountDto accountDto);
}
