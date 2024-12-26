package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.RequestMessageInfo;
import org.letscareer.letscareer.domain.nhn.dto.request.report.*;
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
import org.letscareer.letscareer.domain.report.type.ReportPaymentStatus;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationNotificationVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.global.common.utils.redis.utils.RedisUtils;
import org.letscareer.letscareer.global.common.utils.slack.WebhookProvider;
import org.letscareer.letscareer.global.common.utils.slack.dto.ReportWebhookDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateReportApplicationServiceImpl implements CreateReportApplicationService {
    public static final String REPORT_APPLICATION_CACHE_KEY = "report_application";
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;
    private final ReportOptionHelper reportOptionHelper;
    private final ReportApplicationHelper reportApplicationHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final UserHelper userHelper;
    private final TossProvider tossProvider;
    private final NhnProvider nhnProvider;
    private final WebhookProvider webhookProvider;
    private final RedisUtils redisUtils;

    @Override
    public CreateReportApplicationResponseDto execute(User user, Long reportId, CreateReportApplicationRequestDto requestDto) {
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        ReportPrice reportPrice = reportHelper.findReportPriceByReportIdAndType(reportId, requestDto.reportPriceType());
        ReportFeedback reportFeedback = report.getReportFeedback();
        Coupon coupon = couponHelper.findCouponByIdOrNull(requestDto.couponId());

        ReportApplication reportApplication = reportApplicationHelper.createReportApplicationAndSave(requestDto, report, reportPrice, user);
        Payment payment = paymentHelper.createReportPaymentAndSave(requestDto, coupon, reportApplication);
        List<ReportApplicationOption> reportApplicationOptions = createReportApplicationOptions(reportApplication, reportId, requestDto);
        ReportFeedbackApplication reportFeedbackApplication = reportApplicationHelper.createReportFeedbackApplicationAndSave(requestDto, reportFeedback, reportApplication);
        ReportPaymentStatus paymentStatus = ReportPaymentStatus.of(reportApplication, reportFeedbackApplication);

        updateContactEmail(user, requestDto);
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(requestDto.paymentKey(), requestDto.orderId(), requestDto.amount());
        sendKakaoMessages(paymentStatus, report, user, requestDto, reportApplication.getReportPriceType(), reportApplicationOptions, reportFeedbackApplication);

        setReportApplicationCache(user, report, reportApplication, reportApplicationOptions, reportFeedbackApplication, payment);
        sendSlackBot(report, reportApplication, reportApplicationOptions, reportFeedbackApplication, user, payment, paymentStatus);
        return reportMapper.toCreateReportApplicationResponseDto(responseDto);
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

    private void sendKakaoMessages(ReportPaymentStatus paymentStatus, Report report, User user, CreateReportApplicationRequestDto requestDto, ReportPriceType reportPriceType, List<ReportApplicationOption> reportApplicationOptions, ReportFeedbackApplication reportFeedbackApplication) {
        switch (paymentStatus) {
            case REPORT, ALL -> sendPaymentKakaoMessages(paymentStatus, report, user, requestDto, reportPriceType, reportApplicationOptions, reportFeedbackApplication);
            case REPORT_YET -> sendReportYetPaymentKakaoMessage(report, user, requestDto);
            case ALL_YET -> sendFeedbackYetPaymentKakaoMessage(report, user, requestDto);
        }
    }

    private void sendPaymentKakaoMessages(ReportPaymentStatus paymentStatus, Report report, User user, CreateReportApplicationRequestDto requestDto, ReportPriceType reportPriceType, List<ReportApplicationOption> reportApplicationOptions, ReportFeedbackApplication reportFeedbackApplication) {
        List<RequestMessageInfo<?>> messageList = new ArrayList<>();
        ReportPaymentParameter reportPaymentParameter = ReportPaymentParameter.of(user.getName(), requestDto.orderId(), report.getTitle(), Long.valueOf(requestDto.amount()));
        messageList.add(RequestMessageInfo.of(reportPaymentParameter, "report_payment"));

        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplicationOptions);
        ReportNotificationParameter reportNotificationParameter = ReportNotificationParameter.of(user.getName(), report.getTitle(), reportPriceType.getDesc(), reportOptionListStr);
        messageList.add(RequestMessageInfo.of(reportNotificationParameter, "report_notification"));

        if (paymentStatus.equals(ReportPaymentStatus.ALL)) {
            FeedbackNotiParameter feedbackNotiParameter = FeedbackNotiParameter.of(user.getName(), report, reportOptionListStr, reportFeedbackApplication);
            messageList.add(RequestMessageInfo.of(feedbackNotiParameter, "feedback_noti"));
        }
        nhnProvider.sendPaymentKakaoMessages(user, messageList);
    }

    private void sendReportYetPaymentKakaoMessage(Report report, User user, CreateReportApplicationRequestDto requestDto) {
        ReportYetPaymentParameter reportYetPaymentParameter = ReportYetPaymentParameter.of(user.getName(), requestDto.orderId(), report.getTitle(), Long.valueOf(requestDto.amount()));
        nhnProvider.sendKakaoMessage(user, reportYetPaymentParameter, "report_yet_payment");
    }

    private void sendFeedbackYetPaymentKakaoMessage(Report report, User user, CreateReportApplicationRequestDto requestDto) {
        FeedbackYetPaymentParameter feedbackYetPaymentParameter = FeedbackYetPaymentParameter.of(user.getName(), requestDto.orderId(), report.getTitle(), Long.valueOf(requestDto.amount()));
        nhnProvider.sendKakaoMessage(user, feedbackYetPaymentParameter, "feedback_yet_payment");
    }


    private void setReportApplicationCache(User user, Report report, ReportApplication reportApplication, List<ReportApplicationOption> reportApplicationOptionList, ReportFeedbackApplication reportFeedbackApplication, Payment payment) {
        Boolean isFeedbackApplied = !Objects.isNull(reportFeedbackApplication);
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplicationOptionList);
        ReportApplicationNotificationVo notificationVo = ReportApplicationNotificationVo.of(user.getName(), payment, "전체취소", report, reportOptionListStr, isFeedbackApplied);
        redisUtils.setObjectWithExpire(REPORT_APPLICATION_CACHE_KEY + reportApplication.getId(), notificationVo, 8, TimeUnit.DAYS);
    }

    private void sendSlackBot(Report report,
                              ReportApplication reportApplication,
                              List<ReportApplicationOption> reportApplicationOptions,
                              ReportFeedbackApplication reportFeedbackApplication,
                              User user,
                              Payment payment,
                              ReportPaymentStatus paymentStatus) {
        ReportWebhookDto reportWebhookDto = ReportWebhookDto.of(report, reportApplication, reportApplicationOptions, reportFeedbackApplication, user, payment, paymentStatus);
        webhookProvider.sendMessage(reportWebhookDto);
    }
}
