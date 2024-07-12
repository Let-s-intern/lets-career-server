package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.dto.response.CreateApplicationResponseDto;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.ApplicationMapper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.pg.provider.TossProvider;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.withdraw.helper.WithdrawHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service("LIVE_TOSS")
public class TossLiveApplicationServiceImpl implements TossApplicationService {
    private final TossProvider tossProvider;
    private final LiveApplicationHelper liveApplicationHelper;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final WithdrawHelper withdrawHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;
    private final LiveHelper liveHelper;
    private final UserHelper userHelper;

    @Override
    public CreateApplicationResponseDto createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Live live = liveHelper.findLiveByIdOrThrow(programId);
        Price price = priceHelper.findPriceByIdOrThrow(createApplicationRequestDto.paymentInfo().priceId());
        Coupon coupon = couponHelper.findCouponByIdOrNull(createApplicationRequestDto.paymentInfo().couponId());
        liveApplicationHelper.validateExistingApplication(live.getId(), user.getId());
        liveApplicationHelper.validateLiveDuration(live);
        priceHelper.validatePrice(price, coupon, createApplicationRequestDto.paymentInfo().amount());
        TossPaymentsResponseDto responseDto = tossProvider.requestPayments(createApplicationRequestDto.paymentInfo());
        return applicationMapper.toCreateApplicationResponseDto(responseDto);
    }
}
