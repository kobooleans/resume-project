package com.ks.resumeproject.test.repository;

import com.ks.resumeproject.test.domain.TestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    /** selectTest : 프로젝트 명 조회하기. */
    TestDto selectTest();

    List<TestDto> selectTestBatchList();
}
