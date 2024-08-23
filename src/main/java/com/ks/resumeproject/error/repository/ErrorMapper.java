package com.ks.resumeproject.error.repository;

import com.ks.resumeproject.error.domain.ErrorDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ErrorMapper {
    ErrorDto getErrorByCode(String code);
}
