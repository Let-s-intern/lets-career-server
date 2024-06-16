package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.mission.type.MissionType;

import java.time.LocalDateTime;

@Builder
public record DailyMissionVo(
        Long id,
        Integer th,
        String title,
        MissionType type,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String missionTag,
        String description
) {
}
