package org.letscareer.letscareer.domain.mission.dto.request;

import java.time.LocalDate;
import java.util.List;

public record UpdateMissionRequestDto(
        Integer th,
        String title,
        Integer score,
        Integer lateScore,
        LocalDate startDate,
        LocalDate endDate,
        Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList
) {
}
