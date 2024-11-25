package com.ks.resumeproject.batch.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class CustomWriter implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> items) throws Exception {
        // 쓰기 로직 구현
        //System.out.println("write : " + items);
    }
}
