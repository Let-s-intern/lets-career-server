package org.letscareer.letscareer.domain.missiontemplate.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateMissionTemplateRequestDto(
        @NotNull String missionTag,
        @NotEmpty String title,
        @NotEmpty String description,
        @NotEmpty String guide,
        String templateLink,
        String vodLink
) {
}
