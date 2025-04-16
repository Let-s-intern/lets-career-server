package org.letscareer.letscareer.domain.price.dto.request;

import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;

import java.util.List;

public record CreateChallengePriceRequestDto(
        CreatePriceRequestDto priceInfo,
        String title,
        Integer charge,
        Integer refund,
        ChallengePriceType challengePriceType,
        ChallengePricePlanType challengePricePlanType,
        ChallengeParticipationType challengeParticipationType,
        List<Long> challengeOptionIdList
) {
}
