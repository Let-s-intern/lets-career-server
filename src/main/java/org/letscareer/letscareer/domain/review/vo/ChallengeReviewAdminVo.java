package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;

import java.time.LocalDateTime;

public record ChallengeReviewAdminVo(
        Long reviewId,
        LocalDateTime createDate,
        ChallengeType challengeType,
        String title,
        String name,
        Integer score,
        Integer npsScore,
        Boolean isVisible
) {
}
