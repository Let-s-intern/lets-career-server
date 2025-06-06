package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.CreateApplicationResponseDto;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ApplicationMapper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.nhn.dto.request.credit.CreditConfirmParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.credit.CreditRefundParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.live.LiveClassPaymentParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.pg.type.CancelReason;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.LivePriceHelper;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("LIVE")
public class LiveApplicationServiceImpl implements ApplicationService {
    private final LiveApplicationHelper liveApplicationHelper;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;
    private final LiveHelper liveHelper;
    private final LivePriceHelper livePriceHelper;
    private final UserHelper userHelper;
    private final TossProvider tossProvider;
    private final NhnProvider nhnProvider;

    @Override
    public CreateApplicationResponseDto createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Live live = liveHelper.findLiveByIdOrThrow(programId);
        Price price = priceHelper.findPriceByIdOrThrow(createApplicationRequestDto.paymentInfo().priceId());
        Coupon coupon = couponHelper.findCouponByIdOrNull(createApplicationRequestDto.paymentInfo().couponId());
        validateRequestConditionForCreateApplication(live, coupon, price, user, createApplicationRequestDto);
        createEntityAndSave(live, coupon, price, user, createApplicationRequestDto);

        CreatePaymentRequestDto paymentInfo = createApplicationRequestDto.paymentInfo();
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(paymentInfo.paymentKey(), paymentInfo.orderId(), paymentInfo.amount());
        sendPaymentKakaoMessages(live, user, createApplicationRequestDto.paymentInfo());
        return applicationMapper.toCreateApplicationResponseDto(responseDto);
    }

    @Override
    public void cancelApplication(Long applicationId, User user) {
        LiveApplication application = liveApplicationHelper.findLiveApplicationByIdOrThrow(applicationId);
        validateConditionForCancelApplication(application, user);
        Payment payment = application.getPayment();
        Coupon coupon = payment.getCoupon();
        Live live = application.getLive();
        RefundType refundType = RefundType.ofLive(live);
        Integer finalPrice = payment.getFinalPrice();
        Integer cancelAmount = priceHelper.calculateCancelAmount(finalPrice, coupon, refundType);
        tossProvider.cancelPayments(refundType, payment.getPaymentKey(), cancelAmount, CancelReason.CUSTOMER.getDesc());
        sendCreditRefundKakaoMessage(live, user, payment, refundType, finalPrice, cancelAmount);
        application.updateIsCanceled(true);
        payment.updateRefundPrice(cancelAmount);
    }

    private void validateRequestConditionForCreateApplication(Live live, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        liveApplicationHelper.validateExistingApplication(live.getId(), user.getId());
        liveApplicationHelper.validateLiveDuration(live);
        livePriceHelper.validatePrice(price, coupon, requestDto.paymentInfo().amount());
    }

    private void validateConditionForCancelApplication(LiveApplication application, User user) {
        applicationHelper.checkAlreadyCanceled(application);
        applicationHelper.validateAuthorizedUser(application.getUser(), user);
    }

    private void createEntityAndSave(Live live, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        LiveApplication liveApplication = liveApplicationHelper.createLiveApplicationAndSave(requestDto, live, user);
        Payment payment = paymentHelper.createPaymentAndSave(requestDto.paymentInfo(), liveApplication, coupon, price);
        liveApplication.setPayment(payment);
        userHelper.updateContactEmail(user, requestDto.contactEmail());
    }

    public void sendPaymentKakaoMessages(Live live, User user, CreatePaymentRequestDto paymentInfo) {
        CreditConfirmParameter paymentRequestParameter = CreditConfirmParameter.of(user.getName(), live.getTitle(), paymentInfo);
        LiveClassPaymentParameter programRequestParameter = isOnlineLiveClass(live) ? LiveClassPaymentParameter.of(user.getName(), live) : null;
        nhnProvider.sendProgramPaymentKakaoMessages(user, paymentRequestParameter, programRequestParameter, "payment_confirm", "liveclass_payment");
    }

    private void sendCreditRefundKakaoMessage(Live live, User user, Payment payment, RefundType refundType, Integer finalPrice, Integer cancelAmount) {
        CreditRefundParameter requestParameter = CreditRefundParameter.of(user.getName(), payment.getOrderId(), live.getTitle(), refundType, finalPrice, cancelAmount);
        nhnProvider.sendKakaoMessage(user, requestParameter, "payment_refund");
    }

    private boolean isOnlineLiveClass(Live live) {
        ProgressType progressType = live.getProgressType();
        return progressType.equals(ProgressType.ALL) || progressType.equals(ProgressType.ONLINE);
    }
}
