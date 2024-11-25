package com.ks.resumeproject.batch.reader;

import com.ks.resumeproject.test.domain.TestDto;
import com.ks.resumeproject.test.repository.TestMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/*
ItemReader는 배치 작업의 첫 단계로, read() 메서드를 호출해 데이터를 읽어오고, 배치 작업이 끝날 때까지 계속해서 반복 호출됩니다. read() 메서드는 다음과 같은 방식으로 작동합니다.
•	단일 아이템 반환: read() 메서드는 매번 호출될 때마다 하나의 데이터를 반환합니다. 이 데이터를 이후 단계에서 ItemProcessor와 ItemWriter로 전달해 데이터 처리가 이루어집니다.
•	종료 신호: read() 메서드가 null을 반환하면 Spring Batch는 데이터가 더 이상 없다고 판단하여 해당 청크(batch)가 완료된 것으로 처리하고, Step을 종료하거나 다음 단계로 이동합니다.
    1.	데이터베이스에서 읽기: 데이터베이스에서 데이터를 한 줄씩 읽어 처리할 때 사용합니다. 예를 들어, 사용자 목록을 데이터베이스에서 가져와 배치 작업을 수행할 수 있습니다.
	2.	파일에서 읽기: CSV 파일, XML 파일 등에서 데이터를 읽어 배치 처리를 할 때 사용합니다.
	3.	API에서 데이터 가져오기: 외부 API에서 데이터를 읽어와 후속 처리를 할 때 사용할 수 있습니다.
	4.	메시지 큐에서 데이터 가져오기: 메시지 큐(Kafka, RabbitMQ 등)에서 메시지를 읽어와서 배치 작업으로 처리할 수도 있습니다.
*/
@Component
@RequiredArgsConstructor
public class TestReader{

    private final SqlSessionFactory sqlSessionFactory;

    @Bean
    public ItemReader<TestDto> itemReader() throws Exception{
        MyBatisCursorItemReader<TestDto> reader = new MyBatisCursorItemReader<>();
        reader.setQueryId("com.ks.resumeproject.test.repository.TestMapper.selectTestBatchList");
        reader.setSqlSessionFactory(sqlSessionFactory);
        return reader;
    }
}
