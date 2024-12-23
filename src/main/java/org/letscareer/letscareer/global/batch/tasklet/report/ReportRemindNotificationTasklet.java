package org.letscareer.letscareer.global.batch.tasklet.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportRemindParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.service.GetReportApplicationNotificationVoService;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationNotificationVo;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
@StepScope
public class ReportRemindNotificationTasklet implements Tasklet {
    private final GetReportApplicationNotificationVoService getReportApplicationNotificationVoService;
    private final ReportApplicationHelper reportApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[reportApplicationId]}")
    private Long reportApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrElseNull(reportApplicationId);
        ReportApplicationNotificationVo notificationVo = getReportApplicationNotificationVoService.execute(reportApplicationId);
        if(!Objects.isNull(reportApplication) && !Objects.isNull(notificationVo)) {
            ReportRemindParameter reportRemindParameter = ReportRemindParameter.of(notificationVo);
            String templateCode = notificationVo.isFeedbackApplied() ? "feedback_remind" : "report_remind";
            nhnProvider.sendKakaoMessage(reportApplication.getUser(), reportRemindParameter, templateCode);
        }
        return RepeatStatus.FINISHED;
    }
}
