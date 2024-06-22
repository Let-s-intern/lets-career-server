package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;

import java.time.LocalDateTime;

@Builder
public record MissionDetailVo(
        Long id,
        String title,
        MissionStatusType missionStatusType,
        Integer attendanceCount,
        Long lateAttendanceCount,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
