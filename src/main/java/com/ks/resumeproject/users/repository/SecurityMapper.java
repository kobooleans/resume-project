package com.ks.resumeproject.users.repository;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.HierarchyDto;
import com.ks.resumeproject.security.domain.ResourceDto;
import com.ks.resumeproject.security.domain.RoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SecurityMapper {
    AccountDto selectAccount(String userId);

    AccountDto selectUserAccount(String username);

    List<RoleDto> listRole(BigInteger id);

    List<ResourceDto> listResources();

    List<HierarchyDto> listHierarchy();

}
