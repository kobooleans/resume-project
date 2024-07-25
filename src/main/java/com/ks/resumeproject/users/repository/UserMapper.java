package com.ks.resumeproject.users.repository;


import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.users.domain.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface UserMapper {

    String userNameCheck(AccountDto accountDto);

    void signUp(AccountDto accountDto);

    BigInteger getAccountId(AccountDto accountDto);

    void insertPage(PageDto page);
}
