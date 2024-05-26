package org.letscareer.letscareer.domain.missiontemplate.dto.request;

import org.letscareer.letscareer.domain.missiontemplate.type.MissionTemplateType;

public record UpdateMissionTemplateRequestDto(
        MissionTemplateType type,
        String title,
        String description,
        String guide,
        String templateLink
) {
}
