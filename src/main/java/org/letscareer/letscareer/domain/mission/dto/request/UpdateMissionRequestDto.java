package org.letscareer.letscareer.domain.mission.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateMissionRequestDto(
        Integer th,
        String title,
        Integer score,
        Integer lateScore,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long missionTemplateId,
        List<Long> essentialContentsIdList,
        List<Long> additionalContentsIdList
) {
}
