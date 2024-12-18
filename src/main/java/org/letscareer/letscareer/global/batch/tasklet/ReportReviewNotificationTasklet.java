package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportReviewNotificationParameter;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@StepScope
public class ReportReviewNotificationTasklet implements Tasklet {
    private final ReportApplicationHelper reportApplicationHelper;
    private NhnProvider nhnProvider;

    @Value("#{jobParameters[reportApplicationId]}")
    private Long reportApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
        Report report = reportApplication.getReport();
        User user = reportApplication.getUser();

        ReportReviewNotificationParameter reportReviewNotificationParameter = ReportReviewNotificationParameter.of(user.getName(), report.getTitle(), reportApplication.getReportPriceType().getDesc());
        nhnProvider.sendKakaoMessage(user, reportReviewNotificationParameter, "report_review");

        return RepeatStatus.FINISHED;

    }

}
