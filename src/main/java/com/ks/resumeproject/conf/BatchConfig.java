package com.ks.resumeproject.conf;

import com.ks.resumeproject.batch.processor.CustomProcessor;
import com.ks.resumeproject.batch.reader.TestReader;
import com.ks.resumeproject.batch.writer.CustomWriter;
import com.ks.resumeproject.test.domain.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@EnableScheduling  // 스케줄링 활성화
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final JobLauncher jobLauncher;

    private final TestReader testReader;

    @Bean
    public Job testJob()throws Exception {
        JobBuilder jobBuilder = new JobBuilder("test_job", jobRepository);
        SimpleJobBuilder simpleJobBuilder = jobBuilder.start(testStep());
        return simpleJobBuilder.build();
    }

    @Bean
    public Step testStep() throws Exception {
        StepBuilder stepBuilder = new StepBuilder("test_job_step", jobRepository);
        SimpleStepBuilder<TestDto, String> simpleStepBuilder = stepBuilder
                .<TestDto, String>chunk(10, transactionManager)
                .reader(testReader.itemReader())
                .processor(testProcessor())
                .writer(testWriter());
        return simpleStepBuilder.build();
    }

    @Bean
    public ItemProcessor<TestDto, String> testProcessor() {
        return new CustomProcessor();
    }

    @Bean
    public ItemWriter<String> testWriter() {
        return new CustomWriter();
    }

    // 매일 오전 1시에 Job 실행
    // 시간 관련 - 매일 새벽 4시 job log를 위해 재실행되므로 그 이후 1시간동안은 작업 안되도록 설정
    // 운영 반영 시간을 고려하여 배치 시간 설정 필요 - ( 새벽 1시 이후 4시 미만 - 작업 속도도 고려 필요)
    @Scheduled(cron = "0 0 21 * * ?")
    public void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(testJob(), jobParameters);
    }
}
