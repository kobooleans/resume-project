package com.ks.resumeproject.conf;

import com.ks.resumeproject.batch.reader.AccountReader;
import com.ks.resumeproject.batch.reader.FileReader;
import com.ks.resumeproject.batch.reader.TestReader;
import com.ks.resumeproject.batch.repository.BatchMapper;
import com.ks.resumeproject.batch.writer.AccountWriter;
import com.ks.resumeproject.batch.writer.FileWriter;
import com.ks.resumeproject.multi.domain.FileDto;
import com.ks.resumeproject.security.domain.AccountDto;
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

    private final AccountReader accountReader;
    private final FileReader fileReader;

    private final BatchMapper batchMapper;

    @Bean
    public Job accountJob() throws Exception {
        JobBuilder jobBuilder = new JobBuilder("account_job", jobRepository);
        SimpleJobBuilder simpleJobBuilder = jobBuilder.start(accountStep());
        return simpleJobBuilder.build();
    }


    @Bean
    public Step accountStep() throws Exception {
        StepBuilder stepBuilder = new StepBuilder("account_job_step", jobRepository);
        SimpleStepBuilder<AccountDto, AccountDto> simpleStepBuilder = stepBuilder
                .<AccountDto, AccountDto>chunk(10, transactionManager)
                //account에 use_yn이 N이 있을 경우 리스트 조회
                .reader(accountReader.itemReader())
                .processor(item -> {return item;})
                .writer(accountWriter());
        return simpleStepBuilder.build();
    }

    @Bean
    public ItemWriter<AccountDto> accountWriter() {
        return new AccountWriter(batchMapper);
    }

    @Bean
    public Job fileJob() throws Exception {
        JobBuilder jobBuilder = new JobBuilder("file_job", jobRepository);
        SimpleJobBuilder simpleJobBuilder = jobBuilder.start(fileStep());
        return simpleJobBuilder.build();
    }

    @Bean
    public Step fileStep() throws Exception {
        StepBuilder stepBuilder = new StepBuilder("file_job_step", jobRepository);
        SimpleStepBuilder<FileDto, FileDto> simpleStepBuilder = stepBuilder
                .<FileDto, FileDto>chunk(10, transactionManager)
                //account에 use_yn이 N이 있을 경우 리스트 조회
                .reader(fileReader.itemReader())
                .processor(item -> {return item;})
                .writer(fileWriter());
        return simpleStepBuilder.build();
    }

    @Bean
    public ItemWriter<FileDto> fileWriter() {
        return new FileWriter(batchMapper);
    }

/* 대신 item -> {return item}; 사용
    @Bean
    public ItemProcessor<TestDto, String> testProcessor() {
        return new CustomProcessor();
    }
*/

    // 매일 오전 1시에 Job 실행
    // 시간 관련 - 매일 새벽 4시 job log를 위해 재실행되므로 그 이후 1시간동안은 작업 안되도록 설정
    // 운영 반영 시간을 고려하여 배치 시간 설정 필요 - ( 새벽 1시 이후 4시 미만 - 작업 속도도 고려 필요)
    @Scheduled(cron = "0 0 1 * * ?")
    public void runJob() throws Exception {
        /*account 삭제*/
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(accountJob(), jobParameters);

        /*file 삭제*/
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(fileJob(), jobParameters);
    }
}
