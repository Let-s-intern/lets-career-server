package org.letscareer.letscareer.global.batch.config.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ReportIngNotificationTasklet;
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
public class ReportIngNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReportIngNotificationTasklet reportIngNotificationTasklet;

    @Bean
    public Job reportIngNotificationJob() {
        return new JobBuilder("reportIngNotificationJob", jobRepository)
                .start(sendReportIngNotificationStep())
                .build();
    }

    @Bean
    protected Step sendReportIngNotificationStep() {
        return new StepBuilder("sendReportIngNotificationStep", jobRepository)
                .tasklet(reportIngNotificationTasklet, transactionManager)
                .build();
    }
}
