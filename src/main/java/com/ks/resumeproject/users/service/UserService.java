package com.ks.resumeproject.users.service;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;
import org.apache.el.parser.Token;

import java.util.Map;

public interface UserService {
    void signUp(AccountDto accountDto);

    TokenDto signIn(String username, String password);

    boolean checkUsername(AccountDto accountDto);
    boolean checkEmail(AccountDto accountDto);
    boolean checkEmailAuth(AccountDto accountDto);
    Map<String,Object> sendEmail(AccountDto accountDto);
    Map updateEmail(AccountDto accountDto);

    Map checkAccessYn(Map map);

    TokenDto refreshAccessToken(String refreshToken);
}
