package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.repository.PriceRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_CANNOT_CANCELED;
import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.PRICE_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class PriceHelper {
    private final PriceRepository priceRepository;

    public Price findPriceByIdOrThrow(Long priceId) {
        return priceRepository.findById(priceId)
                .orElseThrow(() -> new EntityNotFoundException(PRICE_NOT_FOUND));
    }

    public int calculateCancelAmount(int finalPrice, Coupon coupon, RefundType refundType) {
        if (refundType.equals(RefundType.ZERO)) throw new InvalidValueException(APPLICATION_CANNOT_CANCELED);
        int couponPrice = Objects.isNull(coupon) ? 0 : coupon.getDiscount();
        couponPrice = couponPrice == -1 ? 0 : couponPrice;
        int regularPrice = finalPrice + couponPrice;
        int refundPrice = roundOffNearestTen(((int) (regularPrice * refundType.getPercent())) - couponPrice);
        return Math.max(refundPrice, 0);
    }

    private int roundOffNearestTen(int number) {
        return (number / 10) * 10;
    }
}
