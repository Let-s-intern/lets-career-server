package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.SendReviewMailTasklet;
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
public class ReviewMailJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final SendReviewMailTasklet sendReviewMailTasklet;
    private final UpdateMailStatusTasklet updateMailStatusTasklet;

    @Bean
    public Job reviewMailJob() {
        return new JobBuilder("reviewMailJob", jobRepository)
                .start(sendReviewMailStep())
                .next(updateReviewMailStatusStep())
                .build();
    }

    @Bean
    protected Step sendReviewMailStep() {
        return new StepBuilder("sendReviewMailStep", jobRepository)
                .tasklet(sendReviewMailTasklet, transactionManager)
                .build();
    }

    @Bean
    protected Step updateReviewMailStatusStep() {
        return new StepBuilder("updateReviewMailStatusStep", jobRepository)
                .tasklet(updateMailStatusTasklet, transactionManager)
                .build();
    }
}
