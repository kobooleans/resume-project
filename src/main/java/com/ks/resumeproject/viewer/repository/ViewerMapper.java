package com.ks.resumeproject.viewer.repository;

import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ViewerMapper {

    String selectRandomId(AccountDto accountDto);

}
