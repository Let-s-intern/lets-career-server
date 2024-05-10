package org.letscareer.letscareer.domain.price.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.price.dto.request.CreatePriceRequestDto;
import org.letscareer.letscareer.domain.price.type.AccountType;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.price.type.ChallengePriceType;
import org.letscareer.letscareer.domain.price.type.ChallengeUserType;

import java.time.LocalDateTime;

@Builder
public record ChallengePriceDetailVo(
        Integer price,
        Integer discount,
        String accountNumber,
        LocalDateTime deadline,
        AccountType accountType,
        ChallengePriceType challengePriceType,
        ChallengeUserType challengeUserType,
        ChallengeParticipationType challengeParticipationType
) {
}
