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
import org.letscareer.letscareer.domain.nhn.dto.request.CreditConfirmParameter;
import org.letscareer.letscareer.domain.nhn.dto.request.CreditRefundParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.score.helper.AdminScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserHelper userHelper;
    private final TossProvider tossProvider;
    private final NhnProvider nhnProvider;

    @Override
    public CreateApplicationResponseDto createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(programId);
        Coupon coupon = couponHelper.findCouponByIdOrNull(createApplicationRequestDto.paymentInfo().couponId());
        Price price = priceHelper.findPriceByIdOrThrow(createApplicationRequestDto.paymentInfo().priceId());
        validateConditionForCreateApplication(challenge, coupon, price, user, createApplicationRequestDto);
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(createApplicationRequestDto.paymentInfo());
        createEntityAndSave(challenge, coupon, price, user, createApplicationRequestDto);
        sendCreditConfirmKakaoMessage(challenge, user, responseDto);
        return applicationMapper.toCreateApplicationResponseDto(responseDto);
    }

    @Override
    public void cancelApplication(Long applicationId, User user) {
        ChallengeApplication application = challengeApplicationHelper.findChallengeApplicationByIdOrThrow(applicationId);
        validateConditionForCancelApplication(application, user);
        Payment payment = application.getPayment();
        Challenge challenge = application.getChallenge();
        RefundType refundType = RefundType.ofChallenge(challenge);
        Integer cancelAmount = priceHelper.calculateCancelAmount(payment, refundType);
        TossPaymentsResponseDto responseDto = tossProvider.cancelPayments(refundType, payment.getPaymentKey(), cancelAmount);
        application.updateIsCanceled(true);
        sendCreditRefundKakaoMessage(challenge, user, payment, refundType, responseDto);
    }

    private void validateConditionForCreateApplication(Challenge challenge, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        challengeApplicationHelper.validateExistingApplication(challenge.getId(), user.getId());
        challengeApplicationHelper.validateChallengeDuration(challenge);
        priceHelper.validatePrice(price, coupon, requestDto.paymentInfo().amount());
    }

    private void createEntityAndSave(Challenge challenge, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        ChallengeApplication challengeApplication = challengeApplicationHelper.createChallengeApplicationAndSave(challenge, user);
        Payment payment = paymentHelper.createPaymentAndSave(requestDto.paymentInfo(), challengeApplication, coupon, price.getPrice());
        challengeApplication.setPayment(payment);
        adminScoreHelper.createAdminScoreAndSave(challengeApplication);
        userHelper.updateContactEmail(user, requestDto.contactEmail());
    }

    private void validateConditionForCancelApplication(ChallengeApplication application, User user) {
        applicationHelper.checkAlreadyCanceled(application);
        applicationHelper.validateAuthorizedUser(application.getUser(), user);
    }

    private void sendCreditConfirmKakaoMessage(Challenge challenge, User user, TossPaymentsResponseDto responseDto) {
        CreditConfirmParameter requestParameter = CreditConfirmParameter.of(user.getName(), challenge.getTitle(), responseDto);
        nhnProvider.sendKakaoMessage(user, requestParameter, "payment_confirm");
    }

    private void sendCreditRefundKakaoMessage(Challenge challenge, User user, Payment payment, RefundType refundType, TossPaymentsResponseDto responseDto) {
        CreditRefundParameter requestParameter = CreditRefundParameter.of(user.getName(), payment.getOrderId(), challenge.getTitle(), refundType, payment.getFinalPrice(), responseDto.cancels().get(0).cancelAmount());
        nhnProvider.sendKakaoMessage(user, requestParameter, "payment_refund");
    }
}
