package com.ks.resumeproject.resume.service;

import com.ks.resumeproject.resume.domain.CareerDto;
import com.ks.resumeproject.security.domain.AccountDto;

import java.util.List;

public interface CareerService {
    List<CareerDto> getCareerList(AccountDto accountDto);

    void insertCareer(CareerDto careerDto);

    void updateCareer(CareerDto careerDto);

    void deleteCareer(CareerDto careerDto);
}
