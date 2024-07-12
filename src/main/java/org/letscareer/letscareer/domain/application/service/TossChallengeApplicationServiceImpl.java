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
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.score.helper.AdminScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.withdraw.helper.WithdrawHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE_TOSS")
public class TossChallengeApplicationServiceImpl implements TossApplicationService{
    private final TossProvider tossProvider;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final AdminScoreHelper adminScoreHelper;
    private final ChallengeHelper challengeHelper;
    private final WithdrawHelper withdrawHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;
    private final UserHelper userHelper;

    @Override
    public CreateApplicationResponseDto createApplication(Long programId, User user, CreateApplicationRequestDto requestDto) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(programId);
        Coupon coupon = couponHelper.findCouponByIdOrNull(requestDto.paymentInfo().couponId());
        Price price = priceHelper.findPriceByIdOrThrow(requestDto.paymentInfo().priceId());
        challengeApplicationHelper.validateExistingApplication(challenge.getId(), user.getId());
        challengeApplicationHelper.validateChallengeDuration(challenge);
        priceHelper.validatePrice(price, coupon, requestDto.paymentInfo().amount());
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(requestDto.paymentInfo());
        return applicationMapper.toCreateApplicationResponseDto(responseDto);
    }
}
