package org.letscareer.letscareer.domain.price.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.price.dto.request.CreateChallengePriceRequestDto;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.letscareer.letscareer.domain.price.repository.ChallengePriceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengePriceHelper {
    private final ChallengePriceRepository challengePriceRepository;

    public ChallengePrice createChallengePriceAndSave(CreateChallengePriceRequestDto requestDto,
                                                      Challenge challenge) {
        ChallengePrice challengePrice = ChallengePrice.createChallengePrice(requestDto, challenge);
        return challengePriceRepository.save(challengePrice);
    }
}
