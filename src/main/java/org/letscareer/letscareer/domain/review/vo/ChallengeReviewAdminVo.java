package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;

import java.time.LocalDateTime;

public record ChallengeReviewAdminVo(
        Long reviewId,
        ReviewProgramType reviewProgramType,
        LocalDateTime createDate,
        ChallengeType challengeType,
        String title,
        String name,
        Integer score,
        Integer npsScore,
        String goodPoint,
        String badPoint,
        Boolean isVisible
) {
}
