package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.users.service.RoleHierarchyService;
import com.ks.resumeproject.users.service.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    private final SecurityService securityService;

    public RoleHierarchyServiceImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Transactional
    @Override
    public String findAllHierarchy() {
        /*
        List<RoleHierarchy>

        while (itr.hasNext()) {
            RoleHierarchy roleHierarchy = itr.next();
            if (roleHierarchy.getParent() != null) {
                hierarchyRole.append(roleHierarchy.getParent().getRoleName());
                hierarchyRole.append(" > ");
                hierarchyRole.append(roleHierarchy.getRoleName());
                hierarchyRole.append("\n");
            }
        }
         */
        return "";
    }
}
