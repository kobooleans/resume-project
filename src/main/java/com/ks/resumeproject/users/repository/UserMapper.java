package com.ks.resumeproject.users.repository;


import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.domain.EmailCodeDto;
import com.ks.resumeproject.users.domain.AccountMyPageDto;
import com.ks.resumeproject.users.domain.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    String userNameCheck(AccountDto accountDto);
    String checkEmail(AccountDto accountDto);
    String checkEmailAuth(AccountDto accountDto);
    boolean insertAuthCode(EmailCodeDto emailCodeDto);
    Map selectAuthCode(EmailCodeDto emailCodeDto);
    boolean updateEmail(AccountDto accountDto);
    void signUp(AccountDto accountDto);

    BigInteger getAccountId(AccountDto accountDto);

    void insertPage(PageDto page);

    List<AccountMyPageDto> pageList(BigInteger id);

    Map checkAccessYn(Map map);
}
