package org.letscareer.letscareer.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.dto.request.CreateApplicationRequestDto;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.mapper.LiveApplicationMapper;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.LIVE_BAD_REQUEST;

@RequiredArgsConstructor
@Transactional
@Service("LIVE")
public class LiveApplicationServiceImpl implements ApplicationService {
    private final LiveApplicationHelper liveApplicationHelper;
    private final LiveApplicationMapper liveApplicationMapper;
    private final ApplicationHelper applicationHelper;
    private final LiveHelper liveHelper;
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;

    @Override
    public void createApplication(Long programId, User user, CreateApplicationRequestDto createApplicationRequestDto) {
        Live live = liveHelper.findLiveByIdOrThrow(programId);
        liveApplicationHelper.validateExistingApplication(live.getId(), user.getId());
        validateCreateLiveApplicationDto(createApplicationRequestDto);
        LiveApplication liveApplication = liveApplicationHelper.createLiveApplicationAndSave(createApplicationRequestDto, live, user);
        Coupon coupon = couponHelper.findCouponByIdOrNull(createApplicationRequestDto.paymentInfo().couponId());
        Price price = priceHelper.findPriceByIdOrThrow(createApplicationRequestDto.paymentInfo().priceId());
        Payment payment = paymentHelper.createPaymentAndSave(liveApplication, coupon, price);
        liveApplication.setPayment(payment);
    }

    @Override
    public void deleteApplication(Long applicationId, User user) {
        LiveApplication liveApplication = liveApplicationHelper.findLiveApplicationByIdOrThrow(applicationId);
        applicationHelper.validateAuthorizedUser(liveApplication.getUser(), user);
        liveApplicationHelper.deleteLiveApplication(liveApplication);
    }

    private void validateCreateLiveApplicationDto(CreateApplicationRequestDto createApplicationRequestDto) {
        if(createApplicationRequestDto.motivate().isBlank())
            throw new InvalidValueException(LIVE_BAD_REQUEST);
    }
}
