package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportAutoRefundParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.payment.entity.Payment;
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
public class ReportAutoRefundNotificationTasklet implements Tasklet {
    private final ReportApplicationHelper reportApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[reportApplicationId]}")
    private Long reportApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
        Payment payment = reportApplication.getPayment();
        Report report = reportApplication.getReport();
        User user = reportApplication.getUser();
        ReportAutoRefundParameter reportAutoRefundParameter = ReportAutoRefundParameter.of(user.getName(), payment.getOrderId(), report.getTitle(), payment.getFinalPrice());
        nhnProvider.sendKakaoMessage(user, reportAutoRefundParameter, "report_auto_refund");
        reportApplication.updateIsCanceled(true);
        return RepeatStatus.FINISHED;
    }
}
