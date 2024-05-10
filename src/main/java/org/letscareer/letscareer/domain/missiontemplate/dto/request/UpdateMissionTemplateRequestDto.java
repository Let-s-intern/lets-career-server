package org.letscareer.letscareer.domain.missiontemplate.dto.request;

public record UpdateMissionTemplateRequestDto(
        String title,
        String description,
        String guide,
        String templateLink
) {
}
