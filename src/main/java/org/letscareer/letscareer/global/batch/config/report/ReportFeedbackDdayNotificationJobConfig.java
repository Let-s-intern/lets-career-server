package org.letscareer.letscareer.global.batch.config.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ReportFeedbackDdayNotificationTasklet;
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
public class ReportFeedbackDdayNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReportFeedbackDdayNotificationTasklet reportFeedbackDdayNotificationTasklet;

    @Bean
    public Job reportFeedbackDdayNotificationJob() {
        return new JobBuilder("reportFeedbackDdayNotificationJob", jobRepository)
                .start(sendReportFeedbackDdayNotificationStep())
                .build();
    }

    @Bean
    protected Step sendReportFeedbackDdayNotificationStep() {
        return new StepBuilder("sendReportFeedbackDdayNotificationStep", jobRepository)
                .tasklet(reportFeedbackDdayNotificationTasklet, transactionManager)
                .build();
    }
}
