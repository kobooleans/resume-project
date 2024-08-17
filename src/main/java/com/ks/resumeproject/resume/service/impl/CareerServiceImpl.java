package com.ks.resumeproject.resume.service.impl;

import com.ks.resumeproject.resume.domain.CareerDto;
import com.ks.resumeproject.resume.repository.CareerMapper;
import com.ks.resumeproject.resume.service.CareerService;
import com.ks.resumeproject.security.domain.AccountDto;
import com.ks.resumeproject.security.util.SecurityUtil;
import com.ks.resumeproject.users.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

    private final CareerMapper careerMapper;
    private final SecurityService securityService;

    @Override
    public List<CareerDto> getCareerList(AccountDto accountDto) {

        AccountDto account = securityService.selectUserAccount(accountDto.getUsername());
        accountDto.setId(account.getId());

        return careerMapper.getCareerList(accountDto);
    }

    @Override
    public void insertCareer(CareerDto careerDto) {
        careerDto.setId(SecurityUtil.getAccount().getAccountDto().getId());

        careerMapper.insertCareer(careerDto);

    }

    @Override
    public void updateCareer(CareerDto careerDto) {
        careerMapper.updateCareer(careerDto);
    }

    @Override
    public void deleteCareer(CareerDto careerDto) {
        careerMapper.deleteCareer(careerDto);
    }
}
