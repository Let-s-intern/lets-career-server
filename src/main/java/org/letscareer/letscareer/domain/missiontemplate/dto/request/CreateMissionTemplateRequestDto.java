package org.letscareer.letscareer.domain.missiontemplate.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateMissionTemplateRequestDto(
        @NotEmpty String title,
        @NotEmpty String description,
        @NotEmpty String guide,
        @NotEmpty String templateLink
) {
}
