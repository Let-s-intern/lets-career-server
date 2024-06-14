package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE")
public class ChallengeApplicationServiceImpl implements ApplicationService {
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ApplicationHelper applicationHelper;
    private final ChallengeHelper challengeHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;

    @Override
    public void createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(programId);
        challengeApplicationHelper.validateExistingApplication(challenge.getId(), user.getId());
        ChallengeApplication challengeApplication = challengeApplicationHelper.createChallengeApplicationAndSave(challenge, user);
        Coupon coupon = couponHelper.findCouponByIdOrNull(createApplicationRequestDto.paymentInfo().couponId());
        Price price = priceHelper.findPriceByIdOrThrow(createApplicationRequestDto.paymentInfo().priceId());
        Payment payment = paymentHelper.createPaymentAndSave(challengeApplication, coupon, price);
        challengeApplication.setPayment(payment);
        challengeHelper.updateCurrentCount(challenge, challenge.getCurrentCount() + 1);
    }

    @Override
    public void deleteApplication(Long applicationId, User user) {
        ChallengeApplication challengeApplication = challengeApplicationHelper.findChallengeApplicationByIdOrThrow(applicationId);
        applicationHelper.validateAuthorizedUser(challengeApplication.getUser(), user);
        challengeHelper.updateCurrentCount(challengeApplication.getChallenge(), challengeApplication.getChallenge().getCurrentCount() - 1);
        challengeApplicationHelper.deleteChallengeApplication(challengeApplication);
    }
}
