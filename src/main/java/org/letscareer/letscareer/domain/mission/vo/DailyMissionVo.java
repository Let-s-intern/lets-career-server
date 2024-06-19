package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DailyMissionVo(
        Long id,
        Integer th,
        String title,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String missionTag,
        String description
) {
}
