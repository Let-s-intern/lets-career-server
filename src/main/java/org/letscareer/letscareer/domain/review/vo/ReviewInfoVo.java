package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;

import java.time.LocalDateTime;

public record ReviewInfoVo(
        Long reviewId,
        ReviewProgramType type,
        LocalDateTime createDate,
        String programTitle,
        String programThumbnail,
        ChallengeType challengeType,
        String missionTitle,
        Integer missionTh,

        String attendanceReview,
        String name,
        String wishJob,
        String wishCompany
) {
}
