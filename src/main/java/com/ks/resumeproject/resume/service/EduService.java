package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.EduDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface EduService {
    List<EduDto> selectEdu(AccountDto accountDto);

    void insertEdu(EduDto eduDto);

    void updateEdu(EduDto eduDto);

    void deleteEdu(EduDto eduDto);
}
