package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.RequestMessageInfo;
import org.letscareer.letscareer.domain.nhn.dto.request.report.FeedbackNotiParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportNotificationParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportPaymentParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.dto.res.CreateReportApplicationResponseDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.CreateReportApplicationService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateReportApplicationServiceImpl implements CreateReportApplicationService {
    private final ReportHelper reportHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final ReportApplicationHelper reportApplicationHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final UserHelper userHelper;
    private final TossProvider tossProvider;
    private final NhnProvider nhnProvider;
    private final ReportMapper reportMapper;

    @Override
    public CreateReportApplicationResponseDto execute(User user, Long reportId, CreateReportApplicationRequestDto requestDto) {
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        ReportPrice reportPrice = reportHelper.findReportPriceByReportIdAndType(reportId, requestDto.reportPriceType());
        ReportFeedback reportFeedback = report.getReportFeedback();
        Coupon coupon = couponHelper.findCouponByIdOrNull(requestDto.couponId());

        ReportApplication reportApplication = reportApplicationHelper.createReportApplicationAndSave(requestDto, report, reportPrice, user);
        Payment payment = paymentHelper.createReportPaymentAndSave(requestDto, coupon, reportApplication);
        reportApplication.setPayment(payment);
        List<ReportApplicationOption> reportApplicationOptions = createReportApplicationOptions(reportApplication, reportId, requestDto);
        ReportFeedbackApplication reportFeedbackApplication = reportApplicationHelper.createReportFeedbackApplicationAndSave(requestDto, reportFeedback, reportApplication);

        updateContactEmail(user, requestDto);
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(requestDto.paymentKey(), requestDto.orderId(), requestDto.amount());
        sendPaymentKakaoMessages(report, user, responseDto, reportApplicationOptions, reportFeedbackApplication);
        return reportMapper.toCreateReportApplicationResponseDto(responseDto);
    }

    private void sendPaymentKakaoMessages(Report report, User user, TossPaymentsResponseDto responseDto, List<ReportApplicationOption> reportApplicationOptions, ReportFeedbackApplication reportFeedbackApplication) {
        List<RequestMessageInfo<?>> messageList = new ArrayList<>();
        ReportPaymentParameter reportPaymentParameter = ReportPaymentParameter.of(user.getName(), responseDto.orderId(), report.getTitle(), Long.valueOf(responseDto.totalAmount()));
        messageList.add(RequestMessageInfo.of(reportPaymentParameter, "report_payment"));
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplicationOptions);
        ReportNotificationParameter reportNotificationParameter = ReportNotificationParameter.of(user.getName(), report, reportOptionListStr);
        messageList.add(RequestMessageInfo.of(reportNotificationParameter, "report_notification"));
        if(!Objects.isNull(reportFeedbackApplication)) {
            FeedbackNotiParameter feedbackNotiParameter = FeedbackNotiParameter.of(user.getName(), report, reportOptionListStr, reportFeedbackApplication);
            messageList.add(RequestMessageInfo.of(feedbackNotiParameter, "feedback_noti"));
        }
        nhnProvider.sendPaymentKakaoMessages(user, messageList);
    }

    private List<ReportApplicationOption> createReportApplicationOptions(ReportApplication reportApplication, Long reportId, CreateReportApplicationRequestDto requestDto) {
        List<ReportOption> reportOptions = reportOptionHelper.findReportOptionsByReportIdAndOptionIds(reportId, requestDto.optionIds());
        return reportOptions.stream()
                .map(reportOption -> reportApplicationHelper.createReportApplicationOptionAndSave(reportApplication, reportOption))
                .collect(Collectors.toList());
    }

    private void updateContactEmail(User user, CreateReportApplicationRequestDto requestDto) {
        if (Objects.isNull(requestDto.contactEmail())) return;
        userHelper.updateContactEmail(user, requestDto.contactEmail());
    }
}
