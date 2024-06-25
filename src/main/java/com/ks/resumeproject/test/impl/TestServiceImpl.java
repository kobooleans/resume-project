package com.ks.resumeproject.test.impl;

import com.ks.resumeproject.test.dao.Test;
import com.ks.resumeproject.test.repository.TestMapper;
import com.ks.resumeproject.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;


    @Override
    public Test selectTest() {
        return testMapper.selectTest();
    }
}
