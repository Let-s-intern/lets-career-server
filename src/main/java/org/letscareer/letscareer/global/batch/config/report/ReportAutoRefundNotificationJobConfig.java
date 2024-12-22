package org.letscareer.letscareer.global.batch.config.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ReportAutoRefundNotificationTasklet;
import org.letscareer.letscareer.global.batch.tasklet.ReportAutoRefundTasklet;
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
public class ReportAutoRefundNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReportAutoRefundTasklet reportAutoRefundTasklet;
    private final ReportAutoRefundNotificationTasklet reportAutoRefundNotificationTasklet;

    @Bean
    public Job reportAutoRefundNotificationJob() {
        return new JobBuilder("reportAutoRefundNotificationJob", jobRepository)
                .start(reportAutoRefundStep())
                .on("COMPLETED").to(sendReportAutoRefundNotificationStep())
                .end()
                .build();
    }

    @Bean
    protected Step reportAutoRefundStep() {
        return new StepBuilder("reportAutoRefundStep", jobRepository)
                .tasklet(reportAutoRefundTasklet, transactionManager)
                .build();
    }

    @Bean
    protected Step sendReportAutoRefundNotificationStep() {
        return new StepBuilder("reportAutoRefundNotificationStep", jobRepository)
                .tasklet(reportAutoRefundNotificationTasklet, transactionManager)
                .build();
    }
}
