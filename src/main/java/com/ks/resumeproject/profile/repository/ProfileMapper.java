package com.ks.resumeproject.profile.repository;

import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface ProfileMapper {
    BigInteger getProfile(AccountDto accountDto);

    int setProfile(AccountDto account);

    void updateProfileFileUseYn(AccountDto account);
}
