package com.ks.resumeproject.users.service;

import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.TokenDto;

import java.util.Map;

public interface UserService {
    void signUp(AccountDto accountDto);

    TokenDto signIn(String username, String password);

    boolean checkUsername(AccountDto accountDto);

    Map checkAccessYn(Map map);
}
