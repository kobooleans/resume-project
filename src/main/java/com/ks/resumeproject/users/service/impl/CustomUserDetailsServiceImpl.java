package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.domain.AccountMyPageDto;
import com.ks.resumeproject.users.repository.SecurityMapper;
import com.ks.resumeproject.users.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final SecurityMapper securityMapper;
    private final UserMapper userMapper;

    @Override
    public AccountContext loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountContext accountContext = null;

        AccountDto accountDto = securityMapper.selectAccount(username);

        if(accountDto != null) {
            // TODO: 해당 부분을 통한 로그인 과정을 거치나, 해당 부분 이상여부 다시 한번 확인필요성 존재
            List<GrantedAuthority> roles = new ArrayList<>();

            String roleType = accountDto.getRoleType();
            roles.add(new SimpleGrantedAuthority(roleType));

            List<AccountMyPageDto> accountMyPageDto = userMapper.pageList(accountDto.getId());

            accountContext = new AccountContext(accountDto, roles, accountMyPageDto);
        }else{
            // user 정보가 없을 경우 Exception 처리가 필요하다.
            throw new UsernameNotFoundException("username not found");
        }

        return accountContext;
    }

}
