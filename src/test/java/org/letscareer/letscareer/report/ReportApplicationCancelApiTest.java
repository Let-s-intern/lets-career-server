//package org.letscareer.letscareer.report;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
//import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
//import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
//import org.letscareer.letscareer.domain.application.helper.ReportFeedbackApplicationHelper;
//import org.letscareer.letscareer.domain.coupon.entity.Coupon;
//import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
//import org.letscareer.letscareer.domain.payment.entity.Payment;
//import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
//import org.letscareer.letscareer.domain.payment.type.ReportRefundType;
//import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
//import org.letscareer.letscareer.domain.report.vo.ReportApplicationOptionPriceVo;
//import org.letscareer.letscareer.domain.report.vo.ReportCancelVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class ReportApplicationCancelApiTest {
//    @Autowired
//    private ReportApplicationHelper reportApplicationHelper;
//    @Autowired
//    private ReportFeedbackApplicationHelper reportFeedbackApplicationHelper;
//    @Autowired
//    private PaymentHelper paymentHelper;
//    @Autowired
//    private ReportPriceHelper reportPriceHelper;
//    @Autowired
//    private CouponHelper couponHelper;
//
//    @DisplayName("환불 금액 테스트")
//    @Test
//    void refundTest() {
//        // given
//        Long reportApplicationId = 507L;
//        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
//        ReportFeedbackApplication reportFeedbackApplication = reportFeedbackApplicationHelper.findReportFeedbackApplicationByReportApplicationIdOrElseNull(reportApplicationId);
//        List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos = reportApplicationHelper.findAllReportApplicationOptionPriceVosByReportApplicationId(reportApplication.getId());
//
//        // when
//        ReportCancelVo reportCancelVo = getReportCancelInfo(reportApplication, reportApplicationOptionPriceVos);
//        ReportCancelVo feedbackCancelVo = getFeedbackCancelInfo(reportFeedbackApplication);
//
//        // then
//        assertThat(reportCancelVo.reportRefundType()).isEqualTo(ReportRefundType.ZERO);
//        assertThat(reportCancelVo.cancelAmount()).isEqualTo(0);
//        assertThat(feedbackCancelVo.reportRefundType()).isEqualTo(ReportRefundType.ALL);
//        assertThat(feedbackCancelVo.cancelAmount()).isEqualTo(1000);
//    }
//
//    private ReportCancelVo getReportCancelInfo(ReportApplication reportApplication, List<ReportApplicationOptionPriceVo> reportApplicationOptionPriceVos) {
//        Payment payment = paymentHelper.findPaymentByApplicationIdOrThrow(reportApplication.getId());
//        Coupon coupon = couponHelper.findCouponByIdOrThrow(4L);
//        //Coupon coupon = null;
//        ReportRefundType reportRefundType = ReportRefundType.ofReport(reportApplication, payment);
//        int cancelAmount = reportPriceHelper.calculateReportCancelAmount(reportApplication, reportApplicationOptionPriceVos, coupon, reportRefundType);
//        return ReportCancelVo.of(reportRefundType, cancelAmount);
//    }
//
//    public ReportCancelVo getFeedbackCancelInfo(ReportFeedbackApplication reportFeedbackApplication) {
//        if(Objects.isNull(reportFeedbackApplication)) return ReportCancelVo.of(ReportRefundType.ZERO, 0);
//        ReportRefundType reportRefundType = ReportRefundType.ofFeedback(reportFeedbackApplication);
//        int cancelAmount = reportPriceHelper.calculateFeedbackCancelAmount(reportFeedbackApplication, reportRefundType);
//        return ReportCancelVo.of(reportRefundType, cancelAmount);
//    }
//}
