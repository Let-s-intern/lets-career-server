package org.letscareer.letscareer.domain.challenge.vo;

import lombok.Builder;

@Builder
public record ChallengeEventVo(
        Long challengeId,
        Long bonusMissionId
) {
}
