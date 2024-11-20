package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.repository.ChallengePriceRepository;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.price.error.ChallengePriceErrorCode.CHALLENGE_PRICE_NOT_FOUND;
import static org.letscareer.letscareer.domain.price.error.PriceErrorCode.INVALID_PRICE;

@RequiredArgsConstructor
@Component
public class ChallengePriceHelper {
    private final ChallengePriceRepository challengePriceRepository;

    public ChallengePrice createChallengePriceAndSave(CreateChallengePriceRequestDto requestDto,
                                                      Challenge challenge) {
        ChallengePrice challengePrice = ChallengePrice.createChallengePrice(requestDto, challenge);
        return challengePriceRepository.save(challengePrice);
    }

    public List<ChallengePriceDetailVo> findChallengePriceDetailVos(Long challengeId) {
        return challengePriceRepository.findChallengePriceDetailVos(challengeId);
    }

    public ChallengePrice findChallengePriceByIdOrThrow(Long challengeId) {
        return challengePriceRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_PRICE_NOT_FOUND));
    }

    public void deleteChallengePricesByChallengeId(Long challengeId) {
        challengePriceRepository.deleteAllByChallengeId(challengeId);
    }

    public PriceDetailVo findPriceDetailVoByChallengeId(Long programId) {
        return challengePriceRepository.findPriceDetailVoByChallengeId(programId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_PRICE_NOT_FOUND));
    }

    public ChallengePrice findChallengePriceByPriceIdOrThrow(Long priceId) {
        return challengePriceRepository.findByPriceId(priceId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_PRICE_NOT_FOUND));
    }

    public void validatePrice(Price price, Coupon coupon, String amount) {
        int finalPrice = calculateFinalPrice(price, coupon);
        if (finalPrice != Integer.parseInt(amount)) {
            throw new InvalidValueException(INVALID_PRICE);
        }
    }

    public int calculateFinalPrice(Price price, Coupon coupon) {
        ChallengePrice challengePrice = findChallengePriceByPriceIdOrThrow(price.getId());
        int finalPrice = price.getPrice() - price.getDiscount();
        if(challengePrice.getChallengePriceType().equals(ChallengePriceType.REFUND)) {
            finalPrice += challengePrice.getRefund();
        }
        if (coupon != null) {
            if (coupon.getDiscount() == -1) return 0;
            finalPrice -= coupon.getDiscount();
        }
        return finalPrice;
    }
}
