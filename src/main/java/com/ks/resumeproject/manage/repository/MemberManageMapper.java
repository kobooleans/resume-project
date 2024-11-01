package com.ks.resumeproject.manage.repository;

import com.ks.resumeproject.manage.domain.MemberManageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberManageMapper {
    void updatePw(MemberManageDto memberManageDto);
}
