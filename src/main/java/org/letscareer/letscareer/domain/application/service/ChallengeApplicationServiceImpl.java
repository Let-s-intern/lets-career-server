package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.CreateApplicationResponseDto;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ApplicationMapper;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.challenge.ChallengePaymentParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.credit.CreditConfirmParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.credit.CreditRefundParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.pg.type.CancelReason;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.ChallengePriceHelper;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.score.helper.AdminScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE")
public class ChallengeApplicationServiceImpl implements ApplicationService {
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final AdminScoreHelper adminScoreHelper;
    private final ChallengeHelper challengeHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;
    private final ChallengePriceHelper challengePriceHelper;
    private final UserHelper userHelper;
    private final TossProvider tossProvider;
    private final NhnProvider nhnProvider;

    @Override
    public CreateApplicationResponseDto createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(programId);
        Coupon coupon = couponHelper.findCouponByIdOrNull(createApplicationRequestDto.paymentInfo().couponId());
        Price price = priceHelper.findPriceByIdOrThrow(createApplicationRequestDto.paymentInfo().priceId());
        validateConditionForCreateApplication(challenge, coupon, price, user, createApplicationRequestDto);
        createEntityAndSave(challenge, coupon, price, user, createApplicationRequestDto);

        CreatePaymentRequestDto paymentInfo = createApplicationRequestDto.paymentInfo();
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(paymentInfo.paymentKey(), paymentInfo.orderId(), paymentInfo.amount());
        sendPaymentKakaoMessages(challenge, user, createApplicationRequestDto.paymentInfo());
        return applicationMapper.toCreateApplicationResponseDto(responseDto);
    }

    @Override
    public void cancelApplication(Long applicationId, User user) {
        ChallengeApplication application = challengeApplicationHelper.findChallengeApplicationByIdOrThrow(applicationId);
        validateConditionForCancelApplication(application, user);
        Payment payment = application.getPayment();
        Coupon coupon = payment.getCoupon();
        Challenge challenge = application.getChallenge();
        RefundType refundType = RefundType.ofChallenge(challenge);
        Integer finalPrice = payment.getFinalPrice();
        Integer cancelAmount = priceHelper.calculateCancelAmount(payment, coupon, refundType);
        tossProvider.cancelPayments(refundType, payment.getPaymentKey(), cancelAmount, CancelReason.CUSTOMER.getDesc());
        sendCreditRefundKakaoMessage(challenge, user, payment, refundType, finalPrice, cancelAmount);
        application.updateIsCanceled(true);
        payment.updateRefundPrice(cancelAmount);
    }

    private void validateConditionForCreateApplication(Challenge challenge, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        challengeApplicationHelper.validateExistingApplication(challenge.getId(), user.getId());
        challengeApplicationHelper.validateChallengeDuration(challenge);
        challengePriceHelper.validatePrice(price, coupon, requestDto.paymentInfo().amount());
    }

    private void createEntityAndSave(Challenge challenge, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        ChallengeApplication challengeApplication = challengeApplicationHelper.createChallengeApplicationAndSave(challenge, user);
        Payment payment = paymentHelper.createPaymentAndSave(requestDto.paymentInfo(), challengeApplication, coupon, price);
        challengeApplication.setPayment(payment);
        adminScoreHelper.createAdminScoreAndSave(challengeApplication);
        userHelper.updateContactEmail(user, requestDto.contactEmail());
    }

    private void validateConditionForCancelApplication(ChallengeApplication application, User user) {
        applicationHelper.checkAlreadyCanceled(application);
        applicationHelper.validateAuthorizedUser(application.getUser(), user);
    }

    private void sendPaymentKakaoMessages(Challenge challenge, User user, CreatePaymentRequestDto paymentInfo) {
        CreditConfirmParameter paymentRequestParameter = CreditConfirmParameter.of(user.getName(), challenge.getTitle(), paymentInfo);
        ChallengePaymentParameter programRequestParameter = isLiveChallenge(challenge) ? ChallengePaymentParameter.of(user.getName(), challenge) : null;

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime challengeStartDate = challenge.getStartDate();
        String messageType;
        if (challengeStartDate.minusHours(1).isAfter(today)) { messageType = "challenge_payment"; }
        else { messageType = "challenge_overpay"; }

        nhnProvider.sendProgramPaymentKakaoMessages(user, paymentRequestParameter, programRequestParameter, "payment_confirm", messageType);
    }

    private void sendCreditRefundKakaoMessage(Challenge challenge, User user, Payment payment, RefundType refundType, Integer finalPrice, Integer cancelAmount) {
        CreditRefundParameter requestParameter = CreditRefundParameter.of(user.getName(), payment.getOrderId(), challenge.getTitle(), refundType, finalPrice, cancelAmount);
        nhnProvider.sendKakaoMessage(user, requestParameter, "payment_refund");
    }

    private boolean isLiveChallenge(Challenge challenge) {
        for(ChallengePrice price : challenge.getPriceList())
            if(!price.getChallengeParticipationType().equals(ChallengeParticipationType.LIVE))
                return false;
        return true;
    }
}
