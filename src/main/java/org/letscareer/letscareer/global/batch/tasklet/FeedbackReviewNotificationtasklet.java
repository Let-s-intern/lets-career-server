package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportFeedbackApplicationHelper;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.nhn.dto.request.report.FeedbackReviewNotificationParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@StepScope
public class FeedbackReviewNotificationtasklet implements Tasklet {
    private final ReportFeedbackApplicationHelper reportFeedbackApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[reportFeedbackApplicationId]}")
    private Long reportFeedbackApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ReportFeedbackApplication reportFeedbackApplication = reportFeedbackApplicationHelper.findReportFeedbackApplicationByReportFeedbackApplicationIdOrThrow(reportFeedbackApplicationId);
        ReportApplication reportApplication = reportFeedbackApplication.getReportApplication();
        Report report = reportApplication.getReport();
        User user = reportApplication.getUser();

        FeedbackReviewNotificationParameter feedbackReviewNotificationParameter = FeedbackReviewNotificationParameter.of(user.getName(), report.getTitle(), reportApplication.getReportPriceType().getDesc());
        nhnProvider.sendKakaoMessage(user, feedbackReviewNotificationParameter, "feedback_review");

        reportFeedbackApplication.updateFeedbackStatus(ReportFeedbackStatus.COMPLETED);
        return RepeatStatus.FINISHED;
    }
}
