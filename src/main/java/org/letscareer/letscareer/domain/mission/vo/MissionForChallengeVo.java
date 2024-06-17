package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;

import java.time.LocalDateTime;

@Builder
public record MissionForChallengeVo(
        Long id,
        String title,
        Integer th,
        String missionTag,
        MissionStatusType missionStatusType,
        Integer attendanceCount,
        Integer lateAttendanceCount,
        Integer score,
        Integer lateScore,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
