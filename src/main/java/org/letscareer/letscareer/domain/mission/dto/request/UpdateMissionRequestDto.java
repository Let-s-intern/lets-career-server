package org.letscareer.letscareer.domain.mission.dto.request;

import org.letscareer.letscareer.domain.mission.type.MissionType;

import java.time.LocalDate;
import java.util.List;

public record UpdateMissionRequestDto(
        String title,
        MissionType type,
        Integer refund,
        LocalDate startDate,
        Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList,
        List<Long> limitedContentsIdList
) {
}
