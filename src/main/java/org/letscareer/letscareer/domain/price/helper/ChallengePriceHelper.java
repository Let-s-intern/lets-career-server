package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.repository.ChallengePriceRepository;
import org.letscareer.letscareer.domain.price.vo.ChallengePriceDetailVo;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.price.error.ChallengePriceErrorCode.CHALLENGE_PRICE_NOT_FOUND;

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
}
