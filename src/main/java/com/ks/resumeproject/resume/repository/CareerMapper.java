package com.ks.resumeproject.resume.repository;

import com.ks.resumeproject.resume.domain.CareerDto;
import com.ks.resumeproject.security.domain.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CareerMapper {
    List<CareerDto> getCareerList(AccountDto accountDto);
    boolean isSideProjectYn(AccountDto accountDto);

    void insertCareer(CareerDto careerDto);

    void updateCareer(CareerDto careerDto);

    void deleteCareer(CareerDto careerDto);
}
