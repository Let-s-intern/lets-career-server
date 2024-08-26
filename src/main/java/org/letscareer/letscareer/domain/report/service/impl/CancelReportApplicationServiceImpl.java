package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ReportFeedbackApplicationHelper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.payment.type.ReportRefundType;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
import org.letscareer.letscareer.domain.report.service.CancelReportApplicationService;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;
import org.letscareer.letscareer.domain.report.vo.ReportCancelVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class CancelReportApplicationServiceImpl implements CancelReportApplicationService {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportFeedbackApplicationHelper reportFeedbackApplicationHelper;
    private final ReportPriceHelper reportPriceHelper;
    private final ApplicationHelper applicationHelper;
    private final TossProvider tossProvider;

    @Override
    public void execute(User user, Long reportApplicationId) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
        Payment payment = reportApplication.getPayment();
        ReportFeedbackApplication reportFeedbackApplication = reportFeedbackApplicationHelper.findReportFeedbackApplicationByReportApplicationIdOrElseNull(reportApplication.getId());
        List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos = reportApplicationHelper.findAllReportApplicationOptionPriceVosByReportApplicationId(reportApplication.getId());
        ReportCancelVo reportCancelInfo = getReportCancelInfo(reportApplication, reportApplicationOptionPriceVos, user);
        ReportCancelVo feedbackCancelInfo = getFeedbackCancelInfo(reportFeedbackApplication, user);
        RefundType refundType = getRefundTypeInfo(reportCancelInfo.reportRefundType(), feedbackCancelInfo.reportRefundType());
        int cancelAmount = reportCancelInfo.cancelAmount() + feedbackCancelInfo.cancelAmount();
        tossProvider.cancelPayments(refundType, payment.getPaymentKey(), cancelAmount);
        // TODO::알림톡 발송
        payment.updateRefundPrice(reportCancelInfo.cancelAmount());
        reportApplication.updateIsCanceled(true);
        if(!Objects.isNull(reportFeedbackApplication)) reportFeedbackApplication.updateRefundPrice(feedbackCancelInfo.cancelAmount());
    }

    private void validateConditionForCancelApplication(ReportApplication reportApplication, User user) {
        applicationHelper.checkAlreadyCanceled(reportApplication);
        applicationHelper.validateAuthorizedUser(reportApplication.getUser(), user);
    }

    private void validateConditionForCancelReportFeedbackApplication(ReportFeedbackApplication reportFeedbackApplication, User user) {
        reportFeedbackApplicationHelper.validateAuthorizedUser(reportFeedbackApplication.getReportApplication().getUser(), user);
    }

    private ReportCancelVo getReportCancelInfo(ReportApplication reportApplication, List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos, User user) {
        validateConditionForCancelApplication(reportApplication, user);
        Payment payment = reportApplication.getPayment();
        Coupon coupon = reportApplication.getPayment().getCoupon();
        ReportRefundType reportRefundType = ReportRefundType.ofReport(reportApplication, payment);
        int cancelAmount = reportPriceHelper.calculateReportCancelAmount(reportApplication, reportApplicationOptionPriceVos, coupon, reportRefundType);
        return ReportCancelVo.of(reportRefundType, cancelAmount);
    }

    private ReportCancelVo getFeedbackCancelInfo(ReportFeedbackApplication reportFeedbackApplication, User user) {
        if(Objects.isNull(reportFeedbackApplication)) return ReportCancelVo.of(ReportRefundType.ZERO, 0);
        validateConditionForCancelReportFeedbackApplication(reportFeedbackApplication, user);
        ReportRefundType reportRefundType = ReportRefundType.ofFeedback(reportFeedbackApplication);
        int cancelAmount = reportPriceHelper.calculateFeedbackCancelAmount(reportFeedbackApplication, reportRefundType);
        return ReportCancelVo.of(reportRefundType, cancelAmount);
    }

    private RefundType getRefundTypeInfo(ReportRefundType reportRefundType, ReportRefundType feedbackRefundType) {
        return (reportRefundType.equals(ReportRefundType.ALL) && feedbackRefundType.equals(ReportRefundType.ALL)) ? RefundType.ALL : null;
    }
}
