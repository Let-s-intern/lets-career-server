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
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengeOption;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengePriceOption;
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
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.score.helper.AdminScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.INVALID_PRICE;

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
        validatePrice(price, coupon, Integer.parseInt(requestDto.paymentInfo().amount()));
    }

    private void createEntityAndSave(Challenge challenge, Coupon coupon, Price price, User user, CreateApplicationRequestDto requestDto) {
        ChallengeApplication challengeApplication = challengeApplicationHelper.createChallengeApplicationAndSave(challenge, user);
        ChallengePrice challengePrice = challengePriceHelper.findChallengePriceByIdOrThrow(price.getId());
        Payment payment = paymentHelper.createPaymentAndSave(requestDto.paymentInfo(), challengeApplication, coupon, price);
        payment.setChallengePricePlanType(challengePrice.getChallengePricePlanType());
        challengeApplication.setPayment(payment);
        adminScoreHelper.createAdminScoreAndSave(challengeApplication);
        userHelper.updateContactEmail(user, requestDto.contactEmail());
    }

    private void validatePrice(Price price, Coupon coupon, Integer amount) {
        int finalPrice = calculateFinalPrice(price, coupon);
        if (finalPrice != amount) {
            throw new InvalidValueException(INVALID_PRICE);
        }
    }

    private int calculateFinalPrice(Price price, Coupon coupon) {
        ChallengePrice challengePrice = challengePriceHelper.findChallengePriceByPriceIdOrThrow(price.getId());
        List<ChallengeOption> challengeOptionList = challengePrice.getChallengePriceOptionList().stream()
                .map(challengePriceOption -> challengePriceOption.getChallengeOption()).toList();

        int finalPrice = price.getPrice() - price.getDiscount();
        if (coupon != null) {
            if (coupon.getDiscount() == -1) return 0;
            finalPrice -= coupon.getDiscount();
        }
        if(challengePrice.getChallengePriceType().equals(ChallengePriceType.REFUND)) {
            finalPrice += challengePrice.getRefund();
        }
        for(ChallengeOption challengeOption : challengeOptionList) {
            finalPrice += (challengeOption.getPrice() - challengeOption.getDiscountPrice());
        }

        return finalPrice;
    }

    private void validateConditionForCancelApplication(ChallengeApplication application, User user) {
        applicationHelper.checkAlreadyCanceled(application);
        applicationHelper.validateAuthorizedUser(application.getUser(), user);
    }

    private void sendPaymentKakaoMessages(Challenge challenge, User user, CreatePaymentRequestDto paymentInfo) {
        CreditConfirmParameter paymentRequestParameter = CreditConfirmParameter.of(user.getName(), challenge.getTitle(), paymentInfo);
        ChallengePaymentParameter programRequestParameter = isLiveChallenge(challenge) ? ChallengePaymentParameter.of(user.getName(), challenge) : null;

        String messageType = challenge.getStartDate().minusHours(1).isAfter(LocalDateTime.now()) ? "challenge_payment" : "challenge_overpay";

        if (!isValidLink(challenge.getChatLink())) {
            messageType = "re_" + messageType;
        }
        nhnProvider.sendProgramPaymentKakaoMessages(user, paymentRequestParameter, programRequestParameter, "payment_confirm", messageType);
    }

    private boolean isValidLink(String link) {
        return link != null && (link.startsWith("https://") || link.startsWith("http://"));
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
