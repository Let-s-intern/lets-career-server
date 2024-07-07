package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.repository.PriceRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.PRICE_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class PriceHelper {
    private final PriceRepository priceRepository;

    public Price findPriceByIdOrThrow(Long priceId) {
        return priceRepository.findById(priceId)
                .orElseThrow(() -> new EntityNotFoundException(PRICE_NOT_FOUND));
    }

    public int calculateFinalPrice(Price price, Coupon coupon) {
        int finalPrice = price.getPrice() - price.getDiscount();
        if (coupon != null) {
            if (coupon.getDiscount() == -1) return 0;
            finalPrice -= coupon.getDiscount();
        }
        return finalPrice;
    }
}
