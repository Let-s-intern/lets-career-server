package org.letscareer.letscareer.domain.attendance.vo;

import org.letscareer.letscareer.domain.challenge.type.ChallengeType;

import java.time.LocalDateTime;

public record MissionReviewAdminVo(
        Long programId,
        Long attendanceId,
        LocalDateTime createDate,
        ChallengeType challengeType,
        String challengeTitle,
        Integer missionTh,
        String missionTitle,
        String name,
        String review,
        Boolean reviewIsVisible
) {
}
