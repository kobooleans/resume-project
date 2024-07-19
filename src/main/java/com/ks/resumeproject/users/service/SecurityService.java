package com.ks.resumeproject.users.service;


import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.RoleDto;

import java.math.BigInteger;
import java.util.List;

public interface SecurityService {

    AccountDto selectAccount(String userId);

    List<RoleDto> listRole(BigInteger userId);

}
