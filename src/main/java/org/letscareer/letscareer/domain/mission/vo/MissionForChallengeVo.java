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
        Long attendanceCount,
        Long lateAttendanceCount,
        Integer score,
        Integer lateScore,
        Long missionTemplateId,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
