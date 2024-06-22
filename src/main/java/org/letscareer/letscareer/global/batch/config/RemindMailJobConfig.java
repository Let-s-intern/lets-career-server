package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.SendRemindMailTasklet;
import org.letscareer.letscareer.global.batch.tasklet.UpdateMailStatusTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class RemindMailJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final SendRemindMailTasklet sendRemindMailTasklet;
    private final UpdateMailStatusTasklet updateMailStatusTasklet;

    @Bean
    public Job remindMailJob() {
        return new JobBuilder("remindMailJob", jobRepository)
                .start(sendRemindMailStep())
                .next(updateRemindMailStatusStep())
                .build();
    }

    @Bean
    protected Step sendRemindMailStep() {
        return new StepBuilder("sendRemindMailStep", jobRepository)
                .tasklet(sendRemindMailTasklet, transactionManager)
                .build();
    }

    @Bean
    protected Step updateRemindMailStatusStep() {
        return new StepBuilder("updateRemindMailStatusStep", jobRepository)
                .tasklet(updateMailStatusTasklet, transactionManager)
                .build();
    }
}
