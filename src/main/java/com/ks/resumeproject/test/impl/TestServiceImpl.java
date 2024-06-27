package com.ks.resumeproject.test.impl;

import com.ks.resumeproject.test.repository.TestMapper;
import com.ks.resumeproject.test.service.TestService;
import com.ks.resumeproject.test.domain.TestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;


    @Override
    public TestVO selectTest() {
        return testMapper.selectTest();
    }
}
