package com.ks.resumeproject.users.service;

import com.ks.resumeproject.security.domain.AccountDto;

public interface UserService {
    void signUp(AccountDto accountDto);
}
