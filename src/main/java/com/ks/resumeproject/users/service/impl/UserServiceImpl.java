package com.ks.resumeproject.users.service.impl;

import com.ks.resumeproject.error.domain.ErrorCode;
import com.ks.resumeproject.error.domain.ErrorDto;
import com.ks.resumeproject.error.exception.CustomCodeException;
import com.ks.resumeproject.error.exception.CustomException;
import com.ks.resumeproject.security.domain.AccountContext;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import com.ks.resumeproject.security.provider.TokenProvider;
import com.ks.resumeproject.users.domain.AccountMyPageDto;
import com.ks.resumeproject.users.domain.PageDto;
import com.ks.resumeproject.users.repository.UserMapper;
import com.ks.resumeproject.users.service.UserService;
import com.ks.resumeproject.util.ComUtil;
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
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ComUtil comUtil;

    @Override
    public boolean checkUsername(AccountDto accountDto) {
        return userMapper.userNameCheck(accountDto).equals("Y");
    }

    @Override
    public Map checkAccessYn(Map map) {
        AccountDto accountDto = new AccountDto();

        accountDto.setUsername(map.get("username").toString());
        map.put("id",userMapper.getAccountId(accountDto));

        return userMapper.checkAccessYn(map);
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        Authentication authentication = jwtTokenProvider.getAuthenticationFromRefresh(refreshToken);
        List<AccountMyPageDto> accountMyPageDto = userMapper.pageList(new BigInteger(((AccountContext)authentication.getPrincipal()).getAccountDto().getId().toString()));

        // 새로운 Access Token 발급
        return jwtTokenProvider.generateToken(authentication, accountMyPageDto).getAccessToken();
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
            pageDto.setRandomId(comUtil.addRandomVal(10));
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

        List<AccountMyPageDto> accountMyPageDto = userMapper.pageList(new BigInteger(((AccountContext)authentication.getPrincipal()).getAccountDto().getId().toString()));

        return jwtTokenProvider.generateToken(authentication, accountMyPageDto);
    }



}
