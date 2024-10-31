package com.ks.resumeproject.manage.service.impl;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.manage.repository.CopyProjectMapper;
import com.ks.resumeproject.manage.service.CopyProjectService;
import com.ks.resumeproject.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyProjectServiceImpl implements CopyProjectService {

    private final CopyProjectMapper copyProjectMapper;
    private final SecurityUtil securityUtil;


    @Override
    public List<ProjectDto> selectProjectList() {
        return copyProjectMapper.selectProjectList(securityUtil.getAccount().getAccountDto());
    }
}
