package org.letscareer.letscareer.domain.challengeoption.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengeOption;
import org.letscareer.letscareer.domain.challengeoption.entity.ChallengePriceOption;
import org.letscareer.letscareer.domain.challengeoption.repository.ChallengePriceOptionRepository;
import org.letscareer.letscareer.domain.price.entity.ChallengePrice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengePriceOptionHelper {
    private final ChallengePriceOptionRepository challengePriceOptionRepository;

    public void createChallengePriceOptionAndSave(ChallengePrice challengePrice, ChallengeOption challengeOption) {
        ChallengePriceOption challengePriceOption = ChallengePriceOption.createChallengePriceOption(challengePrice, challengeOption);
        challengePriceOptionRepository.save(challengePriceOption);
    }
}
