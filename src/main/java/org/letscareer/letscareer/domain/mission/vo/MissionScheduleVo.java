package org.letscareer.letscareer.domain.mission.vo;

import org.letscareer.letscareer.domain.mission.type.MissionStatusType;

import java.time.LocalDateTime;

public record MissionScheduleVo(
        Long id,
        String title,
        Integer th,
        LocalDateTime startDate,
        LocalDateTime endDate,
        MissionStatusType status
) {
}
