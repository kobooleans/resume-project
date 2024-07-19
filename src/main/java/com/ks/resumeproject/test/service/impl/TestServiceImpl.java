package com.ks.resumeproject.test.service.impl;

import com.ks.resumeproject.test.domain.TestDto;
import com.ks.resumeproject.test.repository.TestMapper;
import com.ks.resumeproject.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;


    @Override
    public TestDto selectTest() {
        return testMapper.selectTest();
    }
}
