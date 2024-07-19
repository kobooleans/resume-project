package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.exception.CustomAuthenticationException;
import com.ks.resumeproject.users.repository.UserMapper;
import com.ks.resumeproject.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserMapper userMapper;

    @Override
    public void signUp(AccountDto accountDto) {
        // 패스워드 재검증
        if(accountDto.getPassword() == null || accountDto.getPassword().isEmpty()){
            throw new CustomAuthenticationException("Password is empty");
        }

        // 아이디 재검증
        if(userMapper.userNameCheck(accountDto).equals("Y")){
            accountDto.setRoleType("ROLE_USER");
            accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            userMapper.signUp(accountDto);
        }else{
            throw new CustomAuthenticationException("Username is duplicated");
        }
    }

}
