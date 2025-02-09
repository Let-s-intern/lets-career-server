package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;

import java.time.LocalDateTime;

public record ReviewInfoVo(
        Long reviewId,
        ReviewProgramType type,
        LocalDateTime createDate,
        Long programId,
        String programTitle,
        String programThumbnail,
        ChallengeType challengeType,
        ReportType reportType,
        String missionTitle,
        Integer missionTh,

        String attendanceReview,
        String name,
        String wishJob,
        String wishCompany
) {
}
