package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;

import java.time.LocalDateTime;

@Builder
public record MissionForChallengeVo(
        Long id,
        Integer th,
        MissionStatusType missionStatusType,
        Integer attendanceCount,
        Integer lateAttendanceCount,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
