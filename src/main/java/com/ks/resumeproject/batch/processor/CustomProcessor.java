package com.ks.resumeproject.batch.processor;

import com.ks.resumeproject.test.domain.TestDto;
import org.springframework.batch.item.ItemProcessor;

public class CustomProcessor implements ItemProcessor<TestDto, String> {

    @Override
    public String process(TestDto item) throws Exception {
        // 처리로직 구현
        System.out.println("item : " + item);
        return item.getName().toUpperCase();
    }
}
