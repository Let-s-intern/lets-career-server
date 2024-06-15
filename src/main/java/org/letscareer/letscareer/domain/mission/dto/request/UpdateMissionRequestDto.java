package org.letscareer.letscareer.domain.mission.dto.request;

import org.letscareer.letscareer.domain.mission.type.MissionType;

import java.time.LocalDate;
import java.util.List;

public record UpdateMissionRequestDto(
        Integer th,
        String title,
        MissionType type,
        Integer score,
        Integer lateScore,
        LocalDate startDate,
        Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList,
        List<Long> limitedContentsIdList
) {
}
