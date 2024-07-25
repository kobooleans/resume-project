package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.security.domain.HierarchyDto;
import com.ks.resumeproject.users.repository.SecurityMapper;
import com.ks.resumeproject.users.service.RoleHierarchyService;
import com.ks.resumeproject.users.service.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    private final SecurityMapper securityMapper;

    public RoleHierarchyServiceImpl(SecurityMapper securityMapper) {
        this.securityMapper = securityMapper;
    }

    @Transactional
    @Override
    public String findAllHierarchy() {

        List<HierarchyDto> hierarchyDtos = securityMapper.listHierarchy();

        Iterator<HierarchyDto> itr = hierarchyDtos.iterator();
        StringBuilder hierarchyRole = new StringBuilder();

        while (itr.hasNext()) {
            HierarchyDto roleHierarchy = itr.next();
            if (roleHierarchy.getParentId() != null) {
                hierarchyRole.append(roleHierarchy.getParentType());
                hierarchyRole.append(" > ");
                hierarchyRole.append(roleHierarchy.getRoleType());
                hierarchyRole.append("\n");
            }
        }

        return hierarchyRole.toString();
    }
}
