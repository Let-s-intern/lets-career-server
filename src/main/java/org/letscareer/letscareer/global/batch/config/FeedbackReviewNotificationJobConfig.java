package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.FeedbackReviewNotificationtasklet;
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
public class FeedbackReviewNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FeedbackReviewNotificationtasklet feedbackReviewNotificationtasklet;

    @Bean
    public Job feedbackReviewnotificationJob() {
        return new JobBuilder("feedbackReviewNotificationJob", jobRepository)
                .start(feedbackReviewnotificationStep())
                .build();
    }

    @Bean
    protected Step feedbackReviewnotificationStep() {
        return new StepBuilder("sendFeedbackReviewNotificationStep", jobRepository)
                .tasklet(feedbackReviewNotificationtasklet, transactionManager)
                .build();
    }
}
