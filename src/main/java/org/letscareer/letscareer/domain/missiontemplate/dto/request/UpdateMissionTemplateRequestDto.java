package org.letscareer.letscareer.domain.missiontemplate.dto.request;

public record UpdateMissionTemplateRequestDto(
        String missionTag,
        String title,
        String description,
        String guide,
        String templateLink,
        String vodLink
) {
}
