package org.letscareer.letscareer.domain.mission.dto.request;

import org.letscareer.letscareer.domain.mission.type.MissionStatusType;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateMissionRequestDto(
        Integer th,
        String title,
        Integer score,
        Integer lateScore,
        LocalDateTime startDate,
        LocalDateTime endDate,
        MissionStatusType status,
        Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList
) {
}
