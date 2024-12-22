package org.letscareer.letscareer.global.batch.config.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ReportReviewNotificationTasklet;
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
public class ReportReviewNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReportReviewNotificationTasklet reportReviewNotificationTasklet;

    @Bean
    public Job reportReviewNotificationJob() {
        return new JobBuilder("reportReviewNotificationJob", jobRepository)
                .start(reportReviewNotificationStep())
                .build();
    }

    @Bean
    protected Step reportReviewNotificationStep() {
        return new StepBuilder("sendReportReviewNotificationStep", jobRepository)
                .tasklet(reportReviewNotificationTasklet, transactionManager)
                .build();
    }
}
