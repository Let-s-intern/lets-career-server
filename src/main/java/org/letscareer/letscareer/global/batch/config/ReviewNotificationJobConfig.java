package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ReviewNotificationTasklet;
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
@RequiredArgsConstructor
@Configuration
public class ReviewNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReviewNotificationTasklet reviewNotificationTasklet;

    @Bean
    public Job reviewNotificationJob() {
        return new JobBuilder("reviewNotificationJob", jobRepository)
                .start(sendReviewNotificationStep())
                .build();
    }

    @Bean
    protected Step sendReviewNotificationStep() {
        return new StepBuilder("sendReviewNotificationStep", jobRepository)
                .tasklet(reviewNotificationTasklet, transactionManager)
                .build();
    }
}
