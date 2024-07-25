package com.ks.resumeproject.users.service;


import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.ResourceDto;
import com.ks.resumeproject.security.domain.RoleDto;
import com.ks.resumeproject.security.domain.TokenDto;

import java.math.BigInteger;
import java.util.List;

public interface SecurityService {

    AccountDto selectAccount(String userId);

    List<RoleDto> listRole(BigInteger userId);

    List<ResourceDto> listResources();

}
