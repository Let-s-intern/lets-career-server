package org.letscareer.letscareer.domain.review.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;

import java.time.LocalDateTime;

public record ChallengeReviewVo(
        @JsonIgnore
        Long userId,
        Long programId,
        Long reviewId,
        LocalDateTime createDate,
        ChallengeType challengeType,
        String title,
        Integer score,
        Integer npsScore
) {
}
