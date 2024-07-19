package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.RoleDto;
import com.ks.resumeproject.users.repository.SecurityMapper;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final SecurityMapper securityMapper;

    @Override
    public AccountDto selectAccount(String userId) {
        return securityMapper.selectAccount(userId);
    }

    @Override
    public List<RoleDto> listRole(BigInteger id) {
        return securityMapper.listRole(id);
    }
}
