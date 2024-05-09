package org.letscareer.letscareer.domain.price.dto.request;

import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.price.type.ChallengeUserType;

public record CreateChallengePriceRequestDto(
        CreatePriceRequestDto priceInfo,
        ChallengePriceType challengePriceType,
        ChallengeUserType challengeUserType,
        ChallengeParticipationType challengeParticipationType
) {
}
