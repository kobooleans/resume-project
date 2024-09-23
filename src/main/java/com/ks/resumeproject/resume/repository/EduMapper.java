package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.EduDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EduMapper {

    List<EduDto> selectEdu(AccountDto accountDto);

    void insertEdu(EduDto eduDto);

    void updateEdu(EduDto eduDto);

    void deleteEdu(EduDto eduDto);
}
