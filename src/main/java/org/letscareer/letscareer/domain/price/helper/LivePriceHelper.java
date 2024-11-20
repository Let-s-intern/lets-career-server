package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.price.dto.request.CreateLivePriceRequestDto;
import org.letscareer.letscareer.domain.price.entity.LivePrice;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.error.PriceErrorCode;
import org.letscareer.letscareer.domain.price.repository.LivePriceRepository;
import org.letscareer.letscareer.domain.price.vo.LivePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.INVALID_PRICE;

@RequiredArgsConstructor
@Component
public class LivePriceHelper {
    private final LivePriceRepository livePriceRepository;

    public LivePrice createLivePriceAndSave(CreateLivePriceRequestDto requestDto,
                                            Live live) {
        LivePrice livePrice = LivePrice.createLivePrice(requestDto, live);
        return livePriceRepository.save(livePrice);
    }

    public LivePriceDetailVo findLivePriceDetailVos(Long liveId) {
        return livePriceRepository.findLivePriceDetailVo(liveId)
                .orElseThrow(() -> new EntityNotFoundException(PriceErrorCode.LIVE_PRICE_NOT_FOUND));
    }

    public void deleteLivePriceByLiveId(Long liveId) {
        livePriceRepository.deleteAllByLiveId(liveId);
    }

    public PriceDetailVo findLivePriceDetailVoByLiveId(Long programId) {
        return livePriceRepository.findPriceDetailVoByLiveId(programId)
                .orElseThrow(() -> new EntityNotFoundException(PriceErrorCode.LIVE_PRICE_NOT_FOUND));

    }

    public void validatePrice(Price price, Coupon coupon, String amount) {
        int finalPrice = calculateFinalPrice(price, coupon);
        if (finalPrice != Integer.parseInt(amount)) {
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
}
