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

import static org.letscareer.letscareer.domain.application.error.ApplicationErrorCode.APPLICATION_CANNOT_CANCELED;
import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.PRICE_NOT_FOUND;
import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.INVALID_PRICE;

@RequiredArgsConstructor
@Component
public class PriceHelper {
    private final PriceRepository priceRepository;

    public Price findPriceByIdOrThrow(Long priceId) {
        return priceRepository.findById(priceId)
                .orElseThrow(() -> new EntityNotFoundException(PRICE_NOT_FOUND));
    }

    public void validatePrice(Price price, Coupon coupon, String amount) {
        int finalPrice = calculateFinalPrice(price, coupon);
        if(finalPrice != Integer.parseInt(amount)) {
            throw new InvalidValueException(INVALID_PRICE);
        }
    }

    public int calculateFinalPrice(Price price, Coupon coupon) {
        int finalPrice = price.getPrice() - price.getDiscount();
        if (coupon != null) {
            if (coupon.getDiscount() == -1) return 0;
            finalPrice -= coupon.getDiscount();
        }
        return finalPrice;
    }

    public int calculateCancelAmount(Payment payment, RefundType refundType) {
        System.out.println(refundType);
        if(refundType.equals(RefundType.ZERO))
            throw new InvalidValueException(APPLICATION_CANNOT_CANCELED);
        return (int) (payment.getFinalPrice() * refundType.getPercent()) / 10 * 10;
    }
}
