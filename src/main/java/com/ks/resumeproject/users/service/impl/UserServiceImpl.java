package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.provider.TokenProvider;
import com.ks.resumeproject.users.domain.PageDto;
import com.ks.resumeproject.users.repository.UserMapper;
import com.ks.resumeproject.users.service.UserService;
import com.ks.resumeproject.util.WebUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private WebUtil webUtil;

    @Override
    public boolean checkUsername(AccountDto accountDto) {
        return userMapper.userNameCheck(accountDto).equals("Y");
    }

    @Override
    public void signUp(AccountDto accountDto) {
        // 패스워드 재검증
        if(accountDto.getPassword() == null || accountDto.getPassword().isEmpty()){
            throw new AuthenticationServiceException("Password is empty");
        }

        // 아이디 재검증
        if(userMapper.userNameCheck(accountDto).equals("Y")){
            accountDto.setRoleType("ROLE_USER");
            accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));

            PageDto pageDto = new PageDto();

            userMapper.signUp(accountDto);

            // 기본 Main 페이지 생성
            pageDto.setUsername(accountDto.getUsername());
            pageDto.setRandomId(webUtil.addRandomVal());
            pageDto.setAccountId(userMapper.getAccountId(accountDto));
            pageDto.setPageId(new BigInteger("0"));
            pageDto.setPageDescription("MAIN");
            pageDto.setUseYn("Y");

            userMapper.insertPage(pageDto);

        }else{
            throw new AuthenticationServiceException("Username is duplicated");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TokenDto signIn(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);


        if(authentication == null || !passwordEncoder.matches(password, ((AccountContext)authentication.getPrincipal()).getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return jwtTokenProvider.generateToken(authentication);
    }



}
