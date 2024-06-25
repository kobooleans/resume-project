package com.ks.resumeproject.test.repository;

import com.ks.resumeproject.test.dao.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    /** selectTest : 프로젝트 명 조회하기. */
    public Test selectTest();
}
