package org.letscareer.letscareer.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ReportFeedbackApplicationHelper;
import org.letscareer.letscareer.global.batch.config.report.*;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportNotificationScheduler {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportFeedbackApplicationHelper reportFeedbackApplicationHelper;
    private final JobLauncher jobLauncher;
    private final ReportFeedbackDdayNotificationJobConfig reportFeedbackDdayNotificationJobConfig;
    private final ReportIngNotificationJobConfig reportIngNotificationJobConfig;
    private final ReportRemindNotificationJobConfig reportRemindNotificationJobConfig;
    private final ReportLastRemindNotificationJobConfig reportLastRemindNotificationJobConfig;
    private final ReportAutoRefundNotificationJobConfig reportAutoRefundNotificationJobConfig;
    private final ReportReviewNotificationJobConfig reportReviewNotificationJobConfig;
    private final FeedbackReviewNotificationJobConfig feedbackReviewNotificationJobConfig;


    @Scheduled(cron = "0 0 8 * * ?")
    @SchedulerLock(name = "reportFeedbackDdayNotificationJob", lockAtMostFor = "3m", lockAtLeastFor = "3m")
    public void sendReportFeedbackDdayNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportFeedbackApplicationList = reportFeedbackApplicationHelper.findDdayNotificationReportFeedbackApplicationIds();
        for(Long reportFeedbackApplicationId: reportFeedbackApplicationList) {
            jobLauncher.run(
                    reportFeedbackDdayNotificationJobConfig.reportFeedbackDdayNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportFeedbackApplicationId", reportFeedbackApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

//    @Scheduled(fixedRate = 60000)
//    @SchedulerLock(name = "reportIngNotificationJob", lockAtMostFor = "59s", lockAtLeastFor = "59s")
//    @Scheduled(cron = "30 0,10,20,30,40,50 * * * *")
    @Scheduled(cron = "0 0/1 * * * *")
    @SchedulerLock(name = "reportIngNotificationJob", lockAtMostFor = "59s", lockAtLeastFor = "59s")
    public void sendReportIngNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportApplicationList = reportApplicationHelper.findIngNotificationReportApplicationIds();
        for(Long reportApplicationId : reportApplicationList) {
            jobLauncher.run(
                    reportIngNotificationJobConfig.reportIngNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportApplicationId", reportApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "30 10 10 * * *")
    @SchedulerLock(name = "reportRemindNotificationJob", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void sendReportRemindNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportApplicationList = reportApplicationHelper.findRemindNotificationReportApplicationIds();
        for(Long reportApplicationId : reportApplicationList) {
            jobLauncher.run(
                    reportRemindNotificationJobConfig.reportRemindNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportApplicationId", reportApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    @SchedulerLock(name = "reportLastRemindNotificationJob", lockAtMostFor = "59s", lockAtLeastFor = "59s")
    public void sendReportLastRemindNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportApplicationList = reportApplicationHelper.findLastRemindNotificationReportApplicationIds();
        for(Long reportApplicationId : reportApplicationList) {
            jobLauncher.run(
                    reportLastRemindNotificationJobConfig.reportLastRemindNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportApplicationId", reportApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

//    @Scheduled(cron = "0 0/1 * * * *")
//    @SchedulerLock(name = "reportAutoRefundNotificationJob", lockAtMostFor = "59s", lockAtLeastFor = "59s")
    @Scheduled(cron = "30 0/5 * * * *")
    @SchedulerLock(name = "reportAutoRefundNotificationJob", lockAtMostFor = "4m", lockAtLeastFor = "4m")
    public void sendReportAutoRefundNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportApplicationList = reportApplicationHelper.findAutoRefundNotificationReportApplicationIds();
        for(Long reportApplicationId : reportApplicationList) {
            jobLauncher.run(
                    reportAutoRefundNotificationJobConfig.reportAutoRefundNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportApplicationId", reportApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    @SchedulerLock(name = "reportReviewNotificationJob", lockAtMostFor = "3m", lockAtLeastFor = "3m")
    public void sendReportReviewNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportApplicationList = reportApplicationHelper.findReviewNotificationReportApplicationIds();
        for (Long reportApplicationId : reportApplicationList) {
            jobLauncher.run(
                    reportReviewNotificationJobConfig.reportReviewNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportApplicationId", reportApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    @SchedulerLock(name = "feedbackReviewNotificationJob", lockAtMostFor = "3m", lockAtLeastFor = "3m")
    public void sendFeedbackReviewNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportFeedbackApplicationList = reportFeedbackApplicationHelper.findReviewNotificationReportFeedbackApplicationIds();
        for (Long reportFeedbackApplicationId : reportFeedbackApplicationList) {
            jobLauncher.run(
                    feedbackReviewNotificationJobConfig.feedbackReviewnotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportFeedbackApplicationId", reportFeedbackApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }
}
