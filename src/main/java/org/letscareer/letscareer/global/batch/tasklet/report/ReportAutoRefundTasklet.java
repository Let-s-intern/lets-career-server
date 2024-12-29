package org.letscareer.letscareer.global.batch.tasklet.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.payment.type.ReportRefundType;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.pg.type.CancelReason;
import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;
import org.letscareer.letscareer.domain.report.vo.ReportCancelVo;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
@StepScope
public class ReportAutoRefundTasklet implements Tasklet {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportPriceHelper reportPriceHelper;
    private final ApplicationHelper applicationHelper;
    private final TossProvider tossProvider;

    @Value("#{jobParameters[reportApplicationId]}")
    private Long reportApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrElseNull(reportApplicationId);
        if(!Objects.isNull(reportApplication)) {
            List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos = reportApplicationHelper.findAllReportApplicationOptionPriceVosByReportApplicationId(reportApplication.getId());
            ReportFeedbackApplication reportFeedbackApplication = reportApplication.getReportFeedbackApplication();
            Payment payment = reportApplication.getPayment();
            ReportCancelVo reportCancelInfo = getReportCancelInfo(reportApplication, reportApplicationOptionPriceVos);
            ReportCancelVo feedbackCancelInfo = getFeedbackCancelInfo(reportFeedbackApplication);

            int cancelAmount = payment.getFinalPrice();
            if(!payment.getPaymentKey().isEmpty()) tossProvider.cancelPayments(RefundType.ALL, payment.getPaymentKey(), cancelAmount, CancelReason.CUSTOMER.getDesc());
            payment.updateRefundPrice(cancelAmount);
            updateReportApplicationCancelInfo(reportApplication, reportCancelInfo);
            updateReportFeedbackApplicationCancelInfo(reportFeedbackApplication, feedbackCancelInfo);
        }
        return RepeatStatus.FINISHED;
    }

    private ReportCancelVo getReportCancelInfo(ReportApplication reportApplication, List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos) {
        validateConditionForCancelApplication(reportApplication);
        Coupon coupon = reportApplication.getPayment().getCoupon();
        ReportRefundType reportRefundType = ReportRefundType.ALL;
        int cancelAmount = reportPriceHelper.calculateReportCancelAmount(reportApplication, reportApplicationOptionPriceVos, coupon, reportRefundType);
        return ReportCancelVo.of(reportRefundType, cancelAmount);
    }

    private ReportCancelVo getFeedbackCancelInfo(ReportFeedbackApplication reportFeedbackApplication) {
        if(Objects.isNull(reportFeedbackApplication)) return ReportCancelVo.of(ReportRefundType.ZERO, 0);
        ReportRefundType reportRefundType = ReportRefundType.ALL;
        int cancelAmount = reportPriceHelper.calculateFeedbackCancelAmount(reportFeedbackApplication, reportRefundType);
        return ReportCancelVo.of(reportRefundType, cancelAmount);
    }

    private void validateConditionForCancelApplication(ReportApplication reportApplication) {
        applicationHelper.checkAlreadyCanceled(reportApplication);
    }

    private void updateReportApplicationCancelInfo(ReportApplication reportApplication, ReportCancelVo reportCancelInfo) {
        reportApplication.updateIsCanceled(true);
        reportApplication.updateRefundPrice(reportCancelInfo.cancelAmount());
    }

    private void updateReportFeedbackApplicationCancelInfo(ReportFeedbackApplication reportFeedbackApplication, ReportCancelVo feedbackCancelInfo) {
        if(Objects.isNull(reportFeedbackApplication)) return;
        reportFeedbackApplication.updateIsCanceled(true);
        reportFeedbackApplication.updateRefundPrice(feedbackCancelInfo.cancelAmount());
    }
}
