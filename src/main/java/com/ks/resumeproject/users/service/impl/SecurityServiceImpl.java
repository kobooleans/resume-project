package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.ResourceDto;
import com.ks.resumeproject.security.domain.RoleDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.provider.TokenProvider;
import com.ks.resumeproject.users.repository.SecurityMapper;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<ResourceDto> listResources() {
        return securityMapper.listResources();
    }


}
