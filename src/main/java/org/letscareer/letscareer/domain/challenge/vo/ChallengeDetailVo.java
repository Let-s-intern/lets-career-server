package org.letscareer.letscareer.domain.challenge.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;

import java.time.LocalDateTime;

@Builder
public record ChallengeDetailVo(
        Long id,
        String title,
        String shortDesc,
        String desc,
        String criticalNotice,
        Integer participationCount,
        String thumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline,
        String chatLink,
        String chatPassword,
        ChallengeType challengeType
) {
}
