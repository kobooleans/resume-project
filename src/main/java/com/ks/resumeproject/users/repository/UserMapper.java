package com.ks.resumeproject.users.repository;


import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    String userNameCheck(AccountDto accountDto);

    void signUp(AccountDto accountDto);

}
