package com.ks.resumeproject.manage.service.impl;

import com.ks.resumeproject.manage.domain.ProjectDto;
import com.ks.resumeproject.manage.repository.ManageMainMapper;
import com.ks.resumeproject.manage.service.ManageMainService;

import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.util.ComUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerMainServiceImpl implements ManageMainService {

    private final ManageMainMapper manageMainMapper;

    private final SecurityUtil securityUtil;

    @Override
    public List<ProjectDto> selectProjectCalList() {
        return manageMainMapper.selectProjectCalList(securityUtil.getAccount().getAccountDto());
    }

}
